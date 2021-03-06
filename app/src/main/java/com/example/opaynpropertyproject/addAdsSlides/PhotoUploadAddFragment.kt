package com.example.opaynpropertyproject.addAdsSlides

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.`interface`.GetPositionInterface
import com.example.opaynpropertyproject.adapter.property_setup_adapters.ImageUploadRecyclerAdapter
import com.example.opaynpropertyproject.singleton.SingletonObject.propertyFilling
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.DeletePhotoSuccessModel
import com.example.opaynpropertyproject.api_model.ErrorModel
import com.example.opaynpropertyproject.api_model.ImageModel
import com.example.opaynpropertyproject.api_model.ImageUploadModelSuccessfully
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.comman.Utils
import com.example.opaynpropertyproject.home_activity.HomeActivity.Companion.token
import kotlinx.android.synthetic.main.photoupload_add_fragment.*
import kotlinx.android.synthetic.main.pricing_add_fragment.*
import okhttp3.MultipartBody
import java.io.File


class PhotoUploadAddFragment : BaseFragment(), View.OnClickListener, ApiResponse,
    GetPositionInterface {
    val fields = ArrayList<MultipartBody.Part>()
    var imgList = ArrayList<ImageModel>()
    var isImageUploadCode = 0
    val imgHash_list = HashMap<String, Any>()
    var delete_position = 0
    var imageUploadRecyclerAdapter: ImageUploadRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.photoupload_add_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pre_btn3.setOnClickListener(this)
        img_upload.setOnClickListener(this)
        next_btn_ads3.setOnClickListener(this)
        skip_btn3.setOnClickListener(this)
        imageAdapter()
        if (propertyFilling.update_img > 0) {
            show_img_count.text = propertyFilling.update_img.toString()
        }
        setdata()
    }
    private fun setdata() {
        if (propertyFilling.edit_flag && propertyFilling.detailModel?.data!!.size > 0) {
            propertyFilling.detailModel!!.data[0].images.forEach {
                imgList.add(ImageModel(it.id.toString(), it.image))
                show_img_count.text = imgList.size.toString() + "/20"
            }
            imageUploadRecyclerAdapter?.notifyDataSetChanged()
        }
    }

    override fun onClick(v: View?) {

        when (v!!.id) {

            R.id.pre_btn3 -> {
                Keys.IS_BACK_BTN_PRESS = true
                Utils.addReplaceFragment(
                    requireContext(),
                    ProfileAddFragment(),
                    R.id.nav_container1,
                    true,
                    false,
                    false
                )
            }
            R.id.img_upload -> {

                if (imgList.size < 20) {
                    cropImage.launch(
                        options() {
                            setGuidelines(CropImageView.Guidelines.ON)
                            setOutputCompressQuality(70)
                            setOutputCompressFormat(Bitmap.CompressFormat.PNG)
                        }
                    )
                }


            }
            R.id.next_btn_ads3 -> {
                if (imgList.size > 0) {
//                    for go next fragment
                    Utils.addReplaceFragment(
                        requireContext(),
                        PricingAddFragment(),
                        R.id.nav_container1,
                        true,
                        true,
                        true
                    )
                } else {
                    Utils.customSnakebar(finish_btn, getString(R.string.v_uploadimage))
                }


            }
            R.id.skip_btn3 -> {
                Utils.addReplaceFragment(
                    requireContext(),
                    PricingAddFragment(),
                    R.id.nav_container1,
                    true,
                    true,
                    true
                )
            }
        }
    }

    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            // use the returned uri
            val uriContent = result.uriContent
            val uriFilePath = result.getUriFilePath(requireActivity()) // optional usage
            Log.e("rr", uriContent.toString())
            if (uriContent != null) {
                var file = File(uriFilePath)
                Log.e("uploadImagefilecheker", file.length().toString())
                serviceViewModel.getMultiPart(Keys.IMAGE, file!!)?.let { fields.add(it) }
                //                val mProperty_id = propertyFilling.propertyID.toString()
                Log.e("159874525265", "dd54df54f4d56f4d6f")
                serviceViewModel.getMultiPart(
                    Keys.PROPERTY_IMG_ID,
                    propertyFilling.propertyID.toString()
                )?.let { fields.add(it) }
                serviceViewModel.multipartservice(
                    Keys.IMG_END_POINT, requireContext(), fields,
                    Keys.IMG_RED_CODE, true, token, true, this
                )
            }

            //            image.setImageURI(uriContent)
            //            var id = "4" // herer passs the property id
            //            var file = File(uriFilePath)
            //            serviceViewModel.getMultiPart(Keys.IMAGE,file!!)?.let { fields.add(it) }
            //            serviceViewModel.getMultiPart(Keys.PROPERTY_IMG_ID,id!!)?.let { fields.add(it) }
            //
            //            serviceViewModel.multipartservice(Keys.IMG_END_POINT,requireContext(),fields,Keys.IMG_RED_CODE,true,token,true)
        } else {
            // an error occurred
            val exception = result.error
        }
    }

    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {

            Keys.IMG_RED_CODE -> {
                isImageUploadCode = requestcode
                val imgModel = gson.fromJson(response, ImageUploadModelSuccessfully::class.java)
                Utils.customSnakebar(img_upload, imgModel.message)

                imgList.add(
                    ImageModel(
                        imgModel.data.image.id.toString(),
                        imgModel.data.image.image
                    )
                )
                show_img_count.text = imgList.size.toString() + "/20"
                //  imageUploadRecyclerAdapter!!.notifyItemChanged(delete_position)
                imageUploadRecyclerAdapter?.notifyDataSetChanged()

                Log.e("E121212", "done image")

//                rv_img_upload.adapter =
//                    ImageUploadRecyclerAdapter(imgList2!! , requireContext())
            }
            Keys.IMG_DEL_REQ_CODE -> {
                removeItem(delete_position)
                val imgModel = gson.fromJson(response, DeletePhotoSuccessModel::class.java)
                Log.e("delete", imgModel.message)
            }
            Keys.BACKENDERROR -> {
                val errorModel = gson.fromJson(response, ErrorModel::class.java)
                Utils.customSnakebar(img_upload, errorModel.message)
            }

        }
    }

    override fun getPosition(position: Int) {
        val imgId = imgList[position].image_id
        val propertyId = propertyFilling.propertyID.toString()
        imgHash_list.put(Keys.PROPERTY_ID, imgId)
        imgHash_list.put(Keys.PROPERTY_IMG_ID, propertyId)
        serviceViewModel.deleteserviceBody(
            Keys.IMG_DEL_END_POINT,
            requireContext(),
            imgHash_list,
            Keys.IMG_DEL_REQ_CODE,
            true,
            token,
            true,
            this
        )
        delete_position = position


    }

    fun removeItem(position: Int) {
        if (imgList.size > 1) {
            imgList.removeAt(position)
            propertyFilling.update_img = imgList.size
            ImageUploadRecyclerAdapter(imgList, this, requireContext()).notifyItemChanged(position)
            Log.e("doneimageimage", "removeImages")
        } else {
            Log.e("empt", "NoImages")
            imgList.clear()

            imageAdapter()
        }
        show_img_count.text = imgList.size.toString() + "/20"

    }

    fun imageAdapter() {
        imageUploadRecyclerAdapter = ImageUploadRecyclerAdapter(imgList, this, requireContext())
        rv_img_upload.adapter = imageUploadRecyclerAdapter
    }

}