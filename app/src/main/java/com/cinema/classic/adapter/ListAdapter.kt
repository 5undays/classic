//package com.cinema.classic.adapter
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseAdapter
//import android.widget.ImageView
//import android.widget.TextView
//import com.bumptech.glide.Glide
//import com.cinema.classic.R
//import com.cinema.classic.model.Movie
//
//class ListAdapter (val context:Context, val list: ArrayList<Movie>) :BaseAdapter() {
////    override fun getCount(): Int {
////        return list.size
////    }
////
////    override fun getItem(p0: Int): Any {
////        return list[p0]
////    }
////
////    override fun getItemId(p0: Int): Long {
////        return 0
////    }
////
////    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
////        /* LayoutInflater는 item을 Adapter에서 사용할 View로 부풀려주는(inflate) 역할을 한다. */
////        val view: View = LayoutInflater.from(context).inflate(R.layout.listview_custom, null)
////
////        /* 위에서 생성된 view를 res-layout-main_lv_item.xml 파일의 각 View와 연결하는 과정이다. */
////        val title = view.findViewById<TextView>(R.id.title)
////        val poster = view.findViewById<ImageView>(R.id.poster)
////        val release = view.findViewById<TextView>(R.id.release)
////
////        /* ArrayList<Dog>의 변수 dog의 이미지와 데이터를 ImageView와 TextView에 담는다. */
////        val movie = list[p0]
////        //val resourceId = context.resources.getIdentifier(movie.poster, "drawable", context.packageName)
////        //poster.setImageResource(resourceId)
////        title.text = movie.title
////        release.text = movie.release
////        Glide.with(context)
////            .load(movie.poster)
////            .into(poster)
////        return view
////    }
//}