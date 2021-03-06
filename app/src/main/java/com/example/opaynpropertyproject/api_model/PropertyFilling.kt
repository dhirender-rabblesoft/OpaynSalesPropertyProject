package com.example.opaynpropertyproject.api_model

import android.net.Uri
import android.os.Bundle
import com.example.opaynpropertyproject.api_model.seller_home_model.SellerPropertyListingModel

class PropertyFilling {

    var update_property: Int = 0
    var editpost : SellerPropertyListingModel.Data?=null
    var detailModel : PropertyDetailModel?=null

    var selectedEditProperty: List<SellerPropertyListingModel.Data>? = null
    var edit_id: Int = 0
    var edit_flag: Boolean = false
    var fullPropertySetUpModel: List<SellerPropertyListingModel.Data>? = null
    var update_img: Int = 0
    var list_of_img: ArrayList<ImageUploadModelSuccessfully>? = null
    var propertyID: Int = 0
    var userID: Int = 0
    var property_type_position = 0
    var property_description: String = ""
    var property_price: String = ""
    var sell_type_position: Int = 0
    var sell_property_model_update: SellPropertyModel? = null
    var rv_property_type_list: List<SellPropertyModel.Data.Option>? = null
    var step_one:Boolean = false
    var sell_type: String = ""
    var rv_sell_property_list: List<SellPropertyModel.Data.Option>? = null
    var sell_postion = 0

    var property_type: Int = 0
    var state: String = ""
    var stateID: Int = 0
    var statePosition: Int = 0
    var stateSpinnerList = ArrayList<String>()
    var stateSpinnerModel = ArrayList<StateModel.Data>()

    var city: String = ""
    var citySpinnerList = ArrayList<String>()
    var citySpinnerModel = ArrayList<CityModel.Data>()
    var cityId = 0
    var cityPosition = 0
    var address: String = ""
    var pinCode: String = ""
    var poessionType: String = ""
    var area: String = ""
    var postedby = ""
    var age_of_property = ""
    var entrance = ""
    var furnish = ""
    var sqft:Float = 0.0f
      var no_bed = 0.0f
    var no_bathroom = 0.0f

    //ADS 2 SCREEN
    var sell_list_rev: List<SellPropertyModel.Data.Option>? = null
    var area_list_rev: List<SellPropertyModel.Data.Option>? = null
    var postedby_list_rev: List<SellPropertyModel.Data.Option>? = null
    var amenities_list_rv: List<SellPropertyModel.Data.Option> ? = null
    var age_of_property_rv: List<SellPropertyModel.Data.Option>? = null
    var entranceList_rev: List<SellPropertyModel.Data.Option>? = null
    var furnishList_rev: List<SellPropertyModel.Data.Option>? = null
    var sqft_rev: String = ""
    var number_bed_rev: String = ""
    var number_bathroom_rev: String = ""
    var amenties =ArrayList<Int>()
    var AdsAdd2Bundle: Bundle? = null

}