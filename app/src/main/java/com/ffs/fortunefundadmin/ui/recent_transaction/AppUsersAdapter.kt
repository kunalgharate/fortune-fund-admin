package com.ffs.fortunefundadmin.ui.recent_transaction

import android.graphics.Color


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ffs.fortunefundadmin.domain.model.AppUser
import com.ffs.fortunefundadmin.domain.model.InvestmentPlan
import fortunefundadmin.R
import java.util.*

class AppUsersAdapter(private val mList: List<AppUser>) : RecyclerView.Adapter<AppUsersAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.investment_plan_layout, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var appuser = mList [position]

        //    val rnd = Random()
      //  val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))


        var mRandom =  Random(System.currentTimeMillis());

            // This is the base color which will be mixed with the generated one
             var baseColor = Color.WHITE;

            var baseRed = Color.red(baseColor);
            var baseGreen = Color.green(baseColor);
            var baseBlue = Color.blue(baseColor);

            var red = (baseRed + mRandom.nextInt(256)) / 2;
            var green = (baseGreen + mRandom.nextInt(256)) / 2;
            var blue = (baseBlue + mRandom.nextInt(256)) / 2;

          var color = Color.rgb(red, green, blue);
        

        // sets the image to the imageview from our itemHolder class
       // Picasso.get().load(item.imageUrl).fit().into(holder.imageView)
        holder.textView.setText("${appuser.name}  ${appuser.mobile}  ${appuser.userType}")
        holder.investmentCard.setCardBackgroundColor(color)

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {



        //val imageView: ImageView = itemView.findViewById(R.id.title)
        val textView: TextView = itemView.findViewById(R.id.title)
        val investmentCard: CardView = itemView.findViewById(R.id.investment_card)

    }
}
