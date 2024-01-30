package com.example.remote.mapper

import com.example.common.Mapper
import com.example.data.model.NewDTO
import com.example.data.model.SourceDTO
import com.example.remote.model.NewNetwork
import javax.inject.Inject

class NewNetworkDataMapper @Inject constructor():
    Mapper<NewNetwork, NewDTO> {

    override fun from(i: NewNetwork?): NewDTO {
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

    override fun to(o: NewDTO?): NewNetwork {
        return NewNetwork()
    }


}