package com.lee989898.todayeat.src.fooddetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.lee989898.todayeat.R
import com.lee989898.todayeat.ServiceCreator
import com.lee989898.todayeat.databinding.ActivityFoodDetailBinding
import com.lee989898.todayeat.src.detail.DetailActivity
import com.lee989898.todayeat.src.fooddetail.model.ResponseFoodDetail
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodDetailBinding
    private val eventListener = MarkerEventListener(this)

    lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_food_detail)

        val foodId = intent.getIntExtra("foodId", 0)
        getFoodDetailNetwork(foodId)

        mapView = MapView(this)
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.2325781224618740000000000, 127.1880270943115500000000000), true);

        val marker = MapPOIItem()
        marker.itemName = "청년 다방"
        marker.tag = 1
        marker.mapPoint = MapPoint.mapPointWithGeoCoord(37.2325781224618740000000000, 127.1880270943115500000000000)
        marker.markerType = MapPOIItem.MarkerType.BluePin
        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin

        val marker2 = MapPOIItem()
        marker2.itemName = "엽기 떡볶이"
        marker2.tag = 2
        marker2.mapPoint = MapPoint.mapPointWithGeoCoord(37.2360253223934200000000000, 127.1904078627957000000000000)
        marker2.markerType = MapPOIItem.MarkerType.BluePin
        marker2.selectedMarkerType = MapPOIItem.MarkerType.RedPin

        val markers = arrayOf(marker, marker2)
        mapView.addPOIItems(markers)
        mapView.setPOIItemEventListener(eventListener)

        binding.clKakaoMap.addView(mapView)
    }

    class MarkerEventListener(val context: Context): MapView.POIItemEventListener {
        override fun onPOIItemSelected(mapView: MapView?, poiItem: MapPOIItem?) {

        }

        override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, poiItem: MapPOIItem?) {
            // 말풍선 클릭 시 (Deprecated)
            // 이 함수도 작동하지만 그냥 아래 있는 함수에 작성하자
        }

        override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, poiItem: MapPOIItem?, buttonType: MapPOIItem.CalloutBalloonButtonType?) {
            // 말풍선 클릭 시
            val builder = AlertDialog.Builder(context)
            val itemList = arrayOf("들어가기", "취소")
            builder.setTitle("${poiItem?.itemName}")
            builder.setItems(itemList) { dialog, which ->
                when(which) {
                    0 -> context.startActivity(Intent(context, DetailActivity::class.java))
                    1 -> dialog.dismiss()   // 대화상자 닫기
                }
            }
            builder.show()
        }

        override fun onDraggablePOIItemMoved(mapView: MapView?, poiItem: MapPOIItem?, mapPoint: MapPoint?) {
            // 마커의 속성 중 isDraggable = true 일 때 마커를 이동시켰을 경우
        }
    }


    private fun getFoodDetailNetwork(foodId: Int) {

        val call =
            ServiceCreator.foodDetailService.getFoodDetailResult(foodId)

        call.enqueue(object : Callback<ResponseFoodDetail> {

            override fun onResponse(
                call: Call<ResponseFoodDetail>,
                response: Response<ResponseFoodDetail>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()?.result
                    binding.tvFoodName.text = result?.name
//                    Glide.with(this@FoodDetailActivity)
//                        .load(result?.image)
//                        .into(binding.ivFood)
                    result!!.menus[0].locationX
                } else {
                    Toast.makeText(
                        this@FoodDetailActivity,
                        "디테일 API 연결에 실패하셨습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseFoodDetail>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }

        })
    }

}