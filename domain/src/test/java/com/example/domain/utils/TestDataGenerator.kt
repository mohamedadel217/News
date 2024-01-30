package com.example.domain.utils

import com.example.common.PagingModel
import com.example.domain.entity.NewEntity
import com.example.domain.entity.SourceEntity

class TestDataGenerator {

    companion object {

        fun generateNews(): PagingModel<List<NewEntity>> {
            val list = mutableListOf<NewEntity>().apply {
                add(
                    NewEntity(
                        SourceEntity("1", ""),
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
                    NewEntity(
                        SourceEntity("1", ""),
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
                    NewEntity(
                        SourceEntity("1", ""),
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
                    NewEntity(
                        SourceEntity("1", ""),
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
                    NewEntity(
                        SourceEntity("1", ""),
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
                    NewEntity(
                        SourceEntity("1", ""),
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