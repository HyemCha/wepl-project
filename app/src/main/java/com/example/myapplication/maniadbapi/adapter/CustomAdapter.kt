package com.example.myapplication.maniadbapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.RListData
import com.example.myapplication.databinding.CustomListBinding


//// 사진과 텍스트에 데이터 연결하기
//class Data(val profile:Int, val name:String)



// 아이템 하나하나를 리사이클러뷰에 꽂아주는 애
// 커스텀 리스트를 뷰로 만들어서 붙여줌
class CustomAdapter(private val itemList : List<RListData>) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    // 화면에 리스트를 표시해줌
    // v는 리스트 아이템 레이아웃을 의미
    // 생성된 뷰 홀더에 값 지정
    inner class CustomViewHolder(private val binding: CustomListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RListData) {
            binding.cl = item
        }
    }

    // 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // 부모의 화면에서 팽창. custom_list라는 xml파일을 view로 만들기 위한 코드. resource, layout(표시될 뷰, 리사이클러 뷰 자체),
//        val cellForRow = LayoutInflater.from(parent.context).inflate(R.layout.custom_list, parent, false)

        val binding = CustomListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        // CustomViewHolder를 통해 cellForRow를 전달
        return CustomViewHolder(binding)
    }

    // 수정. 데이터를 각각의 아이템에 바인딩
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    // diffUtil 추가 (고유 값인 id로 비교 하는게 좋음. TEST 위해 title로 함)
//    companion object{
//        val diffUtil = object : DiffUtil.ItemCallback<Item>(){
//            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
//                return oldItem.title == newItem.title
//            }
//
//            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
//                return oldItem.hashCode() == newItem.hashCode()
//            }
//        }
//    }
}