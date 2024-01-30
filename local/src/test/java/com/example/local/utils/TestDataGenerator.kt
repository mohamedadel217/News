package com.example.local.utils

import com.example.local.model.NewLocal
import com.example.local.model.SourceLocal

class TestDataGenerator {

    companion object {
        fun generateNews(): List<NewLocal> {
            val list = mutableListOf<NewLocal>().apply {
                add(
                    NewLocal(
                        id = 0,
                        source = SourceLocal("1", ""),
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
                    NewLocal(
                        id = 1,
                        source = SourceLocal("1", ""),
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
                    NewLocal(
                        id = 2,
                        source = SourceLocal("1", ""),
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
                    NewLocal(
                        id = 3,
                        source = SourceLocal("1", ""),
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
                    NewLocal(
                        id = 4,
                        source = SourceLocal("1", ""),
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
                    NewLocal(
                        id = 5,
                        source = SourceLocal("1", ""),
                        "Test1",
                        "Test1",
                        "",
                        "",
                        "",
                        "",
                        ""
                    )
                )
            }

            return list
        }
    }

}