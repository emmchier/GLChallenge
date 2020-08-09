package com.example.logicmarketchallenge.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.logicmarketchallenge.R;
import com.example.logicmarketchallenge.core.entities.Laptop;
import com.example.logicmarketchallenge.databinding.CellLaptopBinding;
import java.util.ArrayList;
import java.util.List;

/*
 * adaptador para Recyclerview en FragmentLaptopList
 */
public class AdapterRecyclerViewLaptops extends RecyclerView.Adapter {

    private List<Laptop> laptpopList;
    private ProductListListener listListener;
    private CellLaptopBinding cell;

    public AdapterRecyclerViewLaptops(ProductListListener listListener) {
        this.laptpopList = new ArrayList<>();
        this.listListener = listListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        CellLaptopBinding productCell = CellLaptopBinding.bind(layoutInflater.inflate(R.layout.cell_laptop, parent, false));
        ProductViewHolder viewHolder = new ProductViewHolder(productCell);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Laptop laptopEntity = laptpopList.get(position);
        ProductViewHolder productViewHolder = (ProductViewHolder) holder;
        productViewHolder.setCellData(laptopEntity);
    }

    @Override
    public int getItemCount() {
        return laptpopList.size();
    }

    public void loadProductList(List<Laptop> laptops) {
        this.laptpopList.clear();
        this.laptpopList.addAll(laptops);
        notifyDataSetChanged();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private CellLaptopBinding binding;

        public ProductViewHolder(CellLaptopBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listListener.showProductDetail(getAdapterPosition());
                }
            });
        }

        public void setCellData(Laptop laptop) {
            Glide.with(itemView).load(laptop.getImage())
                    .placeholder(R.drawable.ic_laptop)
                    .error(R.drawable.ic_laptop)
                    .into(binding.roundedImageProduct);
            binding.textViewProductTitle.setText(laptop.getTitle());
            binding.textViewProductDescription.setText(laptop.getDescription());
        }
    }

    public interface ProductListListener {
        void showProductDetail(Integer position);
    }
}
