package com.dicoding.sipetta.data.pref

data class PostItem(
    val name: String,
    val description: String,
    val label: String,
    val textSim: String,
    val severity: String
)

object DummyDataProvider {
    val dummyData: List<PostItem> = listOf(
        PostItem("aqyun", "Sawinya layu", "Label 1", "Text Sim 1", "Severity 1"),
        PostItem("rista", "Sawinya hijau", "Label 2", "Text Sim 2", "Severity 2"),
        PostItem("maulidin", "Sawinya kering", "Label 3", "Text Sim 3", "Severity 3"),
        // tambahkan item dummy lainnya sesuai kebutuhan
    )
}
