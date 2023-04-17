package com.arbutus.exerboost.activity.continue_new_order.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.address.add_new_address.AddNewAddressActivity;
import com.arbutus.exerboost.activity.continue_new_order.SelectedAddressListener;
import com.arbutus.exerboost.activity.continue_new_order.fragments.address.SelectAddressFragment;
import com.arbutus.exerboost.activity.continue_new_order.fragments.payment.SelectPaymentFragment;
import com.arbutus.exerboost.activity.continue_new_order.fragments.summary.SummaryFragment;
import com.arbutus.exerboost.activity.new_order.NewOrderModel;
import com.arbutus.exerboost.activity.order_placed.OrderPlacedActivity;
import com.arbutus.exerboost.databinding.ActivityContinueNewOrderBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;
import com.arbutus.exerboost.utilities.FragmentController;
import com.arbutus.exerboost.utilities.Validation;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

public class ContinueNewOrderActivity extends AppCompatActivity {

    private ActivityContinueNewOrderBinding binding;

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private int currentFragmentPosition = 1;

    private ContinueNewOderViewModel continueNewOderViewModel ;

    private NewOrderModel newOrderModel;
    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =ActivityContinueNewOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        continueNewOderViewModel = new ViewModelProvider(this).get(ContinueNewOderViewModel.class);
        initListener();

        binding.header.headerTitleTextView.setText("Select Address");

        getBundles();
        //Load Add address fragment initially
        loadAddressFragment();
    }

    private void getBundles() {
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
          newOrderModel =  bundle.getParcelable("order_data");
        }
    }

    private void loadAddressFragment(){
        SelectAddressFragment fragment = new SelectAddressFragment();
        FragmentController.addFragment(fragmentManager,R.id.fragmentContainer,fragment);

        fragment.initSelectAddressListener(new SelectedAddressListener() {
            @Override
            public void onClickAddress(String addressId , String streetAddress) {
                continueNewOderViewModel.setAddressId(addressId);
                newOrderModel.setStreetAddress(streetAddress);
            }
        });
    }

    private void initListener() {

        binding.stepView.getState()
                .selectedTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .animationType(StepView.ANIMATION_CIRCLE)
                .selectedCircleColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .selectedCircleRadius(getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._14sdp))
                .selectedStepNumberColor(ContextCompat.getColor(this, R.color.colorPrimary))
                // You should specify only stepsNumber or steps array of strings.
                // In case you specify both steps array is chosen.
                .steps(new ArrayList<String>() {{
                    add("Address");
                    add("Payment");
                    add("Summary");
                }})
                // You should specify only steps number or steps array of strings.
                // In case you specify both steps array is chosen.
                .stepsNumber(3)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .stepLineWidth(getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._1sdp))
                .textSize(getResources().getDimensionPixelSize(com.intuit.ssp.R.dimen._12ssp))
                .stepNumberTextSize(getResources().getDimensionPixelSize(com.intuit.ssp.R.dimen._16ssp))
                .typeface(ResourcesCompat.getFont(this, R.font.dmsans_medium))
                // other state methods are equal to the corresponding xml attributes
                .commit();

        binding.header.headerBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

                if(fragment instanceof SummaryFragment){
                    currentFragmentPosition--;
                    binding.stepView.go(1,true);
                    binding.stepView.done(false);

                } else if(fragment instanceof SelectPaymentFragment){
                    currentFragmentPosition--;
                    binding.stepView.go(0,true);
                    binding.stepView.done(false);
                }
                onBackPressed();

            }
        });

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentFragmentPosition ==1){

                    if(Validation.isStringEmpty(continueNewOderViewModel.getAddressId())){
                        Toast.makeText(ContinueNewOrderActivity.this, "Please Select Address", Toast.LENGTH_SHORT).show();
                    } else {
                        currentFragmentPosition++;
                        binding.stepView.go(1,true);
                        FragmentController.replaceFragmentWithBackStack(fragmentManager,R.id.fragmentContainer,new SelectPaymentFragment());
                    }

                } else if(currentFragmentPosition ==2){

                    //todo validate card
                    currentFragmentPosition++;
                    binding.stepView.go(2,true);
                    binding.nextButton.setText("Pay Now");

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("order_data",newOrderModel);

                    SummaryFragment summaryFragment = new SummaryFragment();
                    summaryFragment.setArguments(bundle);

                    FragmentController.replaceFragmentWithBackStack(fragmentManager,R.id.fragmentContainer,summaryFragment);

                } else {

                    newOrderModel.setDeliveryAddress(continueNewOderViewModel.getAddressId());

                    if(AppBoilerPlateCode.isInternetConnected(ContinueNewOrderActivity.this)){
                        progressDialog = AppBoilerPlateCode.setProgressDialog(ContinueNewOrderActivity.this);
                        createOrderToServer();
                    }
                    else {
                        AppBoilerPlateCode.showSnackBarForInternet(ContinueNewOrderActivity.this,binding.rootLayoutOfContinueNewOrder);
                    }

                }
            }
        });

    }

    private void createOrderToServer() {

        System.out.println("+++++++++++++++++++++ "+continueNewOderViewModel.toString());
        continueNewOderViewModel.createOrder(newOrderModel);

        LiveData<String> successLiveData = continueNewOderViewModel.getSuccess();

        successLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressDialog.dismiss();
                Toast.makeText(ContinueNewOrderActivity.this, s, Toast.LENGTH_SHORT).show();
                System.out.println("============ SUCCESS ===============");

                AppBoilerPlateCode.navigateToActivityWithFinish(ContinueNewOrderActivity.this, OrderPlacedActivity.class,null);

            }
        });


        LiveData<String> failureLiveData = continueNewOderViewModel.getFailure();

        failureLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressDialog.dismiss();
                Toast.makeText(ContinueNewOrderActivity.this, s, Toast.LENGTH_SHORT).show();
                System.out.println("============ FAILED ===============");
            }
        });
    }

    public void setHeaderTitle(String title){
        binding.header.headerTitleTextView.setText(title);
    }

}