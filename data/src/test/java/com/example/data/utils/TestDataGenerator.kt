package com.example.data.utils

import com.example.common.PagingModel
import com.example.data.model.NewDTO
import com.example.data.model.SourceDTO

class TestDataGenerator {

    companion object {

        fun generateNews(): PagingModel<List<NewDTO>> {
            val list = mutableListOf<NewDTO>().apply {
                add(
                    NewDTO(
                        SourceDTO("1", ""),
                        "Test1",
                        "Test1",
                        "",
                        "",
                        "",
                        "",
                        ""
                    )
                )
                add(
                    NewDTO(
                        SourceDTO("1", ""),
                        "Test2",
                        "Test2",
                        "",
                        "",
                        "",
                        "",
                        ""
                    )
                )
                add(
                    NewDTO(
                        SourceDTO("1", ""),
                        "Test3",
                        "Test3",
                        "",
                        "",
                        "",
                        "",
                        ""
                    )
                )
                add(
                    NewDTO(
                        SourceDTO("1", ""),
                        "Test4",
                        "Test4",
                        "",
                        "",
                        "",
                        "",
                        ""
                    )
                )
                add(
                    NewDTO(
                        SourceDTO("1", ""),
                        "Test5",
                        "Test5",
                        "",
                        "",
                        "",
                        "",
                        ""
                    )
                )
                add(
                    NewDTO(
                        SourceDTO("1", ""),
                        "Test6",
                        "Test6",
                        "",
                        "",
                        "",
                        "",
                        ""
                    )
                )
            }

            return PagingModel(list, total = 6, currentPage = 1)
        }

    }

}