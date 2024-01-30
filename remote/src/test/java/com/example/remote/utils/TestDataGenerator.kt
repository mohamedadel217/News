package com.example.remote.utils

import com.example.remote.model.NewNetwork
import com.example.remote.model.NewsResponseNetwork
import com.example.remote.model.SourceNetwork

class TestDataGenerator {

    companion object {
        fun generateNews(): NewsResponseNetwork {
            val list = mutableListOf<NewNetwork>().apply {
                add(
                    NewNetwork(
                        SourceNetwork("1", ""),
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
                    NewNetwork(
                        SourceNetwork("1", ""),
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
                    NewNetwork(
                        SourceNetwork("1", ""),
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
                    NewNetwork(
                        SourceNetwork("1", ""),
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
                    NewNetwork(
                        SourceNetwork("1", ""),
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
                    NewNetwork(
                        SourceNetwork("1", ""),
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

            return NewsResponseNetwork(
                list
            )
        }
    }

}