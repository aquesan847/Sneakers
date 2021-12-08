package es.aqs.di.ad.finalproject.view.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import es.aqs.di.ad.finalproject.R;
import es.aqs.di.ad.finalproject.databinding.FragmentAddBinding;
import es.aqs.di.ad.finalproject.model.entity.Sneaker;
import es.aqs.di.ad.finalproject.model.entity.SneakerBrand;
import es.aqs.di.ad.finalproject.viewmodel.SneakerViewModel;
import es.aqs.di.ad.finalproject.viewmodel.SneakerBrandViewModel;


public class AddFragment extends Fragment {

    private FragmentAddBinding binding;

    private EditText etSneakersName, etSneakersSize,
            etSneakersBuyDate, etUrl;
    private Spinner spSneakersBrand;
    private ImageView ivVideoGameCover;
    private Sneaker sneaker;
    private SneakerViewModel videoGameVM;
    private SneakerBrand auxVGC;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAddBinding.inflate(inflater, container, false);
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

    private void addVideoGame(Sneaker sneaker) {

        new AlertDialog.Builder(getParentFragment().getContext())
                .setTitle(R.string.alertDialog_add_title)
                .setMessage(R.string.alertDialog_add_message)
                .setPositiveButton(R.string.alertDialog_confirm, (dialog, which) -> {
                    // Añado video juego
                    videoGameVM.insertSneaker(sneaker);
                    Toast.makeText(getParentFragment().getContext(), R.string.toast_addSneakers, Toast.LENGTH_LONG).show();
                })
                .setNegativeButton(R.string.alertDialog_cancel, (dialog, which) -> {
                    dialog.cancel();
                })
                .show();
    }

    private void cleanFields() {
        etSneakersName.setText("");
        spSneakersBrand.setSelection(0);
        etSneakersSize.setText("");
        etSneakersBuyDate.setText("");
        etUrl.setText("");
    }

    private void defineAddListener() {
        binding.btAddVideoGame.setOnClickListener(view -> {
            Sneaker sneaker = getVideoGame();
            if (sneaker.isValid()) {
                addVideoGame(sneaker);
            } else {
                Toast.makeText(getParentFragment().getContext(), R.string.toast_invalidInputData, Toast.LENGTH_LONG).show();
            }
        });
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
            cleanFields();
        });

        SneakerBrandViewModel videoGameConsoleVM = new ViewModelProvider(this).get(SneakerBrandViewModel.class);
        videoGameConsoleVM.getSneakerBrands().observe(getViewLifecycleOwner(), videoGameConsoles -> {
            // videoGameConsoleVM.getVideoGameConsoles().observe(this, videoGameConsoles -> {
            SneakerBrand sneakerBrand = new SneakerBrand();
            sneakerBrand.id = 0;
            sneakerBrand.name = getString(R.string.default_spinnerName);
            videoGameConsoles.add(0, sneakerBrand);
            ArrayAdapter<SneakerBrand> adapter =
                    new ArrayAdapter<SneakerBrand>(getParentFragment().getContext(), android.R.layout.simple_spinner_dropdown_item, videoGameConsoles);
            spSneakersBrand.setAdapter(adapter);
        });
    }

    private void initialize() {
        etSneakersName = binding.etName;
        spSneakersBrand = binding.spinnerBrand;
        etSneakersSize = binding.etSize;
        etSneakersBuyDate = binding.etBuyDate;
        etUrl = binding.etUrl;

        ivVideoGameCover = binding.imgUpload;

        getViewModel();

        defineAddListener();
    }
}