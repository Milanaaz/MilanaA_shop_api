package com.example.milanaa_shop_api.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.milanaa_shop_api.R;
import com.example.milanaa_shop_api.databinding.FragmentHomeBinding;
import com.example.milanaa_shop_api.models.ModelM;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    JemAdapter adapter;
    HomeViewModel homeViewModel;
    NavController navController;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeViewModel.getModelMResponseLiveData().observe(
                getViewLifecycleOwner(), new Observer<List<ModelM>>() {
                    @Override
                    public void onChanged(List<ModelM> modelMS) {
                        binding.progressBar.setVisibility(View.INVISIBLE);

                        adapter = new JemAdapter(requireActivity(), modelMS);
                        binding.rvCatalogM.setAdapter(adapter);

                    }
                }
        );
        setUpOnBackPressed();
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.basketBtn.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(requireActivity(), binding.basketBtn);
            popupMenu.getMenuInflater().inflate(R.menu.action_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getTitle().toString()) {
                        case "Добавить в корзину":
                            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);

                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("basketkey", adapter.getSelected_intoBacketList());
                            navController.navigate(R.id.navigation_backet, bundle);
                            break;

                        case "Пометить":
                            Toast.makeText(requireActivity(), "Marked", Toast.LENGTH_SHORT).show();
                            break;

                        default:
                            Toast.makeText(requireActivity(), "default", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });

            popupMenu.show();
        });
    }


    private void setUpOnBackPressed() {
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (isEnabled()) {
                    requireActivity().finish();
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}