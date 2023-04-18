package com.arbutus.exerboost.activity.main.fragments.order;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.arbutus.exerboost.activity.main.fragments.order.fragments.complete.CompleteOrderFragment;
import com.arbutus.exerboost.activity.main.fragments.order.fragments.pending.PendingOrderFragment;

public class TabLayoutAdapter extends FragmentStateAdapter {

    private CompleteOrderFragment completeOrderFragment;
    private PendingOrderFragment pendingOrderFragment;

    public TabLayoutAdapter(@NonNull Fragment fragment,CompleteOrderFragment completeOrderFragment,PendingOrderFragment pendingOrderFragment) {
        super(fragment);

        this.completeOrderFragment  = completeOrderFragment;
        this.pendingOrderFragment = pendingOrderFragment;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0: return completeOrderFragment;

            case 1: return pendingOrderFragment;
        }
        return new CompleteOrderFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
