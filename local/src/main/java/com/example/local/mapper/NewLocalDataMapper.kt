package com.example.local.mapper

import com.example.common.Mapper
import com.example.data.model.NewDTO
import com.example.data.model.SourceDTO
import com.example.local.model.NewLocal
import javax.inject.Inject

class NewLocalDataMapper @Inject constructor():
    Mapper<NewLocal, NewDTO> {

    override fun from(i: NewLocal?): NewDTO {
        return NewDTO(
            title = i?.title ?: "",
            author = i?.author ?: "",
            description = i?.description ?: "",
            content = i?.content ?: "",
            publishedAt = i?.publishedAt ?: "",
            url = i?.url ?: "",
            urlToImage = i?.urlToImage ?: "",
            source = SourceDTO(
                i?.source?.id ?: "",
                i?.source?.name ?: ""
            )
        )
    }

    override fun to(o: NewDTO?): NewLocal {
        return NewLocal(id = 0)
    }


}