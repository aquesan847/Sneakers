package es.aqs.di.ad.finalproject.view.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.aqs.di.ad.finalproject.R;
import es.aqs.di.ad.finalproject.databinding.FragmentMainBinding;
import es.aqs.di.ad.finalproject.model.entity.Sneaker_SneakerBrand;
import es.aqs.di.ad.finalproject.view.adapter.SneakerAdapter;
import es.aqs.di.ad.finalproject.viewmodel.SneakerViewModel;

import java.util.List;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    private void initialize() {

        RecyclerView recyclerView = binding.rvSneakers;
        recyclerView.setLayoutManager(new LinearLayoutManager(getParentFragment().getContext()));

        SneakerViewModel videoGameVM = new ViewModelProvider(this).get(SneakerViewModel.class);
        SneakerAdapter sneakerAdapter = new SneakerAdapter(getContext());

        recyclerView.setAdapter(sneakerAdapter);

        LiveData<List<Sneaker_SneakerBrand>> videoGameList = videoGameVM.getAllSneakers();
        videoGameList.observe(getViewLifecycleOwner(), videoGames -> {
            sneakerAdapter.setSneakerList(videoGames);
        });

        binding.fabSneakersAdd.setOnClickListener(view -> NavHostFragment.findNavController(MainFragment.this)
                .navigate(R.id.action_mainFragment_to_addFragment));

        sneakerAdapter.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable(getString(R.string.sneakerName), sneakerAdapter.getItem(recyclerView.getChildAdapterPosition(view)).sneaker.name);
            bundle.putSerializable(getString(R.string.sneakerBrand), sneakerAdapter.getItem(recyclerView.getChildAdapterPosition(view)).sneaker.idSneakerBrand);
            bundle.putSerializable(getString(R.string.sneakerSize), sneakerAdapter.getItem(recyclerView.getChildAdapterPosition(view)).sneaker.size);
            bundle.putSerializable(getString(R.string.sneakerBuyDate), sneakerAdapter.getItem(recyclerView.getChildAdapterPosition(view)).sneaker.buyDate);
            bundle.putSerializable(getString(R.string.sneakerUrl), sneakerAdapter.getItem(recyclerView.getChildAdapterPosition(view)).sneaker.imageUrl);
            NavHostFragment.findNavController(MainFragment.this).navigate(R.id.action_mainFragment_to_editFragment,bundle);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}