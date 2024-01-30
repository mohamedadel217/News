package com.example.data.mapper

import com.example.common.Mapper
import com.example.data.model.NewDTO
import com.example.data.model.SourceDTO
import com.example.domain.entity.NewEntity
import com.example.domain.entity.SourceEntity
import javax.inject.Inject

class NewDataDomainMapper @Inject constructor(): Mapper<NewDTO, NewEntity> {

    override fun from(i: NewDTO?): NewEntity {
        return NewEntity(
            title = i?.title ?: "",
            author = i?.author ?: "",
            description = i?.description ?: "",
            content = i?.content ?: "",
            publishedAt = i?.publishedAt ?: "",
            url = i?.url ?: "",
            urlToImage = i?.urlToImage ?: "",
            sourceEntity = SourceEntity(
                i?.source?.id ?: "",
                i?.source?.name ?: ""
            )
        )
    }

    override fun to(o: NewEntity?): NewDTO {
        return NewDTO(
            title = o?.title ?: "",
            author = o?.author ?: "",
            description = o?.description ?: "",
            content = o?.content ?: "",
            publishedAt = o?.publishedAt ?: "",
            url = o?.url ?: "",
            urlToImage = o?.urlToImage ?: "",
            source = SourceDTO(
                o?.sourceEntity?.id ?: "",
                o?.sourceEntity?.name ?: ""
            )
        )
    }

}