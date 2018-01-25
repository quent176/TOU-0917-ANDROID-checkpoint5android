package fr.wcs.wcstravel.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.wcs.wcstravel.Models.ResultTripModel;
import fr.wcs.wcstravel.R;

public class ResultTravelAdapter extends RecyclerView.Adapter<ResultTravelAdapter.MyViewHolder> {

    private Context mContext;
    private List<ResultTripModel> mItem;
    private String mPriceUSD;
    private Double mPriceDoubleUSD;

    public ResultTravelAdapter (List<ResultTripModel> item, Context context){
        this.mItem = item;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_flight_travel, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.display(mItem.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mResultCompany, mResultPrice, mResultPriceEUR;

        public MyViewHolder(View itemView) {
            super(itemView);

            mResultCompany = itemView.findViewById(R.id.result_company);
            mResultPrice = itemView.findViewById(R.id.result_price_USD);
            mResultPriceEUR = itemView.findViewById(R.id.result_price_EUR);

        }

        public void display(ResultTripModel resultTripModel) {

            mResultCompany.setText(resultTripModel.getCompany());
            mResultPrice.setText(resultTripModel.getPrice());
            mPriceUSD = resultTripModel.getPrice();
            mPriceDoubleUSD = Double.parseDouble(mPriceUSD);
            mResultPriceEUR.setText(convertPrice(mPriceDoubleUSD, "USD", "EUR"));

        }
    }

    public String convertPrice(Double inputPrice, String currencyFrom, String currencyTo){
      String newPrice = "";
        if (currencyFrom.equals("USD") && (currencyTo.equals("EUR"))) {
            Double newPriceDouble = inputPrice * 0.80;
            newPrice = String.valueOf(newPriceDouble);
        }
      return newPrice;
    }

}
