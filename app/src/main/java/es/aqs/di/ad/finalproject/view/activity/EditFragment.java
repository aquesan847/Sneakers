package es.aqs.di.ad.finalproject.view.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import es.aqs.di.ad.finalproject.R;
import es.aqs.di.ad.finalproject.databinding.FragmentEditBinding;
import es.aqs.di.ad.finalproject.model.entity.Sneaker;
import es.aqs.di.ad.finalproject.model.entity.SneakerBrand;
import es.aqs.di.ad.finalproject.viewmodel.SneakerBrandViewModel;
import es.aqs.di.ad.finalproject.viewmodel.SneakerViewModel;

public class EditFragment extends Fragment {

    private FragmentEditBinding binding;

    private EditText etSneakersName, etSneakersSize,
            etSneakersBuyDate, etUrl;
    private Spinner spSneakersBrand;
    private ImageView imgUpload;
    private Button btDeleteSneakers, btEditSneakers, btCancelEditSneakers, btSaveEditSneakers;
    private Sneaker sneaker;
    private SneakerViewModel videoGameVM;
    private String nameSneaker;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentEditBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    private void initialize() {

        etSneakersName = binding.etName;
        etSneakersName.setText(String.valueOf(getArguments().getSerializable(getString(R.string.sneakerName))));
        spSneakersBrand = binding.spinnerBrand;
        spSneakersBrand.setSelection(Integer.parseInt(String.valueOf(getArguments().getSerializable(getString(R.string.sneakerBrand)))));
        etSneakersSize = binding.etSize;
        etSneakersSize.setText(String.valueOf(getArguments().getSerializable(getString(R.string.sneakerSize))));
        etSneakersBuyDate = binding.etBuyDate;
        etSneakersBuyDate.setText(String.valueOf(getArguments().getSerializable(getString(R.string.sneakerBuyDate))));
        etUrl = binding.etUrl;
        etUrl.setText(String.valueOf(getArguments().getSerializable(getString(R.string.sneakerUrl))));

        nameSneaker = String.valueOf(getArguments().getSerializable(getString(R.string.sneakerName)));

        imgUpload = binding.imgUpload;

        btDeleteSneakers = binding.btDeleteVideoGame;
        btEditSneakers = binding.btEditVideoGame;
        btSaveEditSneakers = binding.btSaveEditVideoGame;
        btCancelEditSneakers = binding.btCancelEditVideoGame;

        getViewModel();
        defineDeleteListener();
        defineEditListener();
    }

    private void defineDeleteListener() {
        binding.btDeleteVideoGame.setOnClickListener(view -> {
            Sneaker sneaker = getVideoGame();
            deleteVideoGame(sneaker);
        });
    }

    private void defineEditListener() {
        binding.btEditVideoGame.setOnClickListener(view -> editVideoGame());
    }

    private void defineSaveCancelEditListener() {
        binding.btCancelEditVideoGame.setOnClickListener(view -> {
            btDeleteSneakers.setVisibility(View.VISIBLE);
            btEditSneakers.setVisibility(View.VISIBLE);
            btSaveEditSneakers.setVisibility(View.INVISIBLE);
            btCancelEditSneakers.setVisibility(View.INVISIBLE);
        });
    }

    private void defineSaveEditListener() {
        binding.btSaveEditVideoGame.setOnClickListener(view -> {
            Sneaker sneaker = getVideoGame();
            if (sneaker.isValid()) {
                saveEditVideoGame(sneaker);
            } else {
                Toast.makeText(getParentFragment().getContext(), R.string.toast_invalidInputData, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void deleteVideoGame(Sneaker sneaker) {

        new AlertDialog.Builder(getParentFragment().getContext())
                .setTitle(R.string.alertDialog_delete_title)
                .setMessage(R.string.alertDialog_delete_message)
                .setPositiveButton(R.string.alertDialog_confirm, (dialog, which) -> {
                    videoGameVM.deleteSneaker(sneaker);
                    Toast.makeText(getParentFragment().getContext(), R.string.toast_deleteSneakers, Toast.LENGTH_LONG).show();
                    NavHostFragment.findNavController(EditFragment.this).navigate(R.id.action_editFragment_to_mainFragment);
                })
                .setNegativeButton(R.string.alertDialog_cancel, (dialog, which) -> {
                    dialog.cancel();
                })
                .show();
    }

    private void editVideoGame() {
        btDeleteSneakers.setVisibility(View.GONE);
        btEditSneakers.setVisibility(View.GONE);

        btSaveEditSneakers.setVisibility(View.VISIBLE);
        defineSaveEditListener();

        btCancelEditSneakers.setVisibility(View.VISIBLE);
        defineSaveCancelEditListener();
    }


    private void saveEditVideoGame(Sneaker sneaker) {

        new AlertDialog.Builder(getParentFragment().getContext())
                .setTitle(R.string.alertDialog_edit_title)
                .setMessage(R.string.alertDialog_edit_message)
                .setPositiveButton(R.string.alertDialog_confirm, (dialog, which) -> {
                    videoGameVM.updateSneaker(sneaker, nameSneaker);
                    Toast.makeText(getParentFragment().getContext(), R.string.toast_editSneakers, Toast.LENGTH_LONG).show();
                    NavHostFragment.findNavController(EditFragment.this).navigate(R.id.action_editFragment_to_mainFragment);
                })
                .setNegativeButton(R.string.alertDialog_cancel, (dialog, which) -> {
                    dialog.cancel();
                })
                .show();

    }

    private Sneaker getVideoGame() {
        String name = etSneakersName.getText().toString();
        SneakerBrand console = (SneakerBrand) spSneakersBrand.getSelectedItem();
        String genre = etSneakersSize.getText().toString();
        String date = etSneakersBuyDate.getText().toString();
        String url = etUrl.getText().toString();

        sneaker = new Sneaker();
        sneaker.name = name;
        sneaker.idSneakerBrand = console.id;
        sneaker.size = genre;
        sneaker.buyDate = date;
        sneaker.imageUrl = url;

        return sneaker;
    }

    private void getViewModel() {
        videoGameVM = new ViewModelProvider(this).get(SneakerViewModel.class);

        videoGameVM.getInsertResults().observe(getViewLifecycleOwner(), list -> {

        });

        SneakerBrandViewModel videoGameConsoleVM = new ViewModelProvider(this).get(SneakerBrandViewModel.class);
        videoGameConsoleVM.getSneakerBrands().observe(getViewLifecycleOwner(), videoGameConsoles -> {
            // videoGameConsoleVM.getVideoGameConsoles().observe(this, videoGameConsoles -> {
            SneakerBrand sneakerBrand = new SneakerBrand();
            //sneakerBrand.id = 0;
            sneakerBrand.name = getString(R.string.default_spinnerName);
            videoGameConsoles.add(0, sneakerBrand);
            ArrayAdapter<SneakerBrand> adapter =
                    new ArrayAdapter<SneakerBrand>(getParentFragment().getContext(), android.R.layout.simple_spinner_dropdown_item, videoGameConsoles);
            spSneakersBrand.setAdapter(adapter);

            spSneakersBrand = binding.spinnerBrand;
            spSneakersBrand.setSelection(Integer.parseInt(String.valueOf(getArguments().getSerializable(getString(R.string.sneakerBrand)))));

        });
    }

}