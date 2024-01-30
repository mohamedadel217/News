package com.example.feature.mapper

import com.example.common.Mapper
import com.example.common.formatWithDeviceTimeZone
import com.example.domain.entity.NewEntity
import com.example.domain.entity.SourceEntity
import com.example.feature.model.NewUiModel
import com.example.feature.model.SourceUiModel
import javax.inject.Inject

class NewDomainUiMapper @Inject constructor() : Mapper<NewEntity, NewUiModel> {

    override fun from(i: NewEntity?): NewUiModel {
        return NewUiModel(
            title = i?.title ?: "",
            author = i?.author ?: "",
            description = i?.description ?: "",
            content = i?.content ?: "",
            publishedAt = (i?.publishedAt ?: "").formatWithDeviceTimeZone(),
            url = i?.url ?: "",
            urlToImage = i?.urlToImage ?: "",
            sourceUiModel = SourceUiModel(
                id = i?.sourceEntity?.id ?: "",
                name = i?.sourceEntity?.name ?: ""
            )
        )
    }

    override fun to(o: NewUiModel?): NewEntity {
        return NewEntity(
            title = o?.title ?: "",
            author = o?.author ?: "",
            description = o?.description ?: "",
            content = o?.content ?: "",
            publishedAt = o?.publishedAt ?: "",
            url = o?.url ?: "",
            urlToImage = o?.urlToImage ?: "",
            sourceEntity = SourceEntity(
                id = o?.sourceUiModel?.id ?: "",
                name = o?.sourceUiModel?.name ?: ""
            )
        )
    }


}