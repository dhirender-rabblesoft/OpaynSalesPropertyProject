package com.example.opaynpropertyproject.adapters.singleton

import com.example.opaynpropertyproject.api_model.BasicPropertyModelSuccessfully
import com.example.opaynpropertyproject.api_model.PropertyFilling
import com.example.opaynpropertyproject.api_model.SuccessSignupModel

object SingletonObject {
        lateinit var mUserID: SuccessSignupModel
        lateinit var mPropertyID : BasicPropertyModelSuccessfully
        var propertyFilling= PropertyFilling()


}