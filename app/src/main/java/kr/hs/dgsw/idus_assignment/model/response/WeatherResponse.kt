package kr.hs.dgsw.idus_assignment.model.response

data class WeatherResponse(
    val weatherStateName : String,      // 날씨 요약 (ex. 맑음, 흐림... etc)
    val weatherStateAbbr : String,      // 아이콘 이미지 (URL 형태로 날아옴)
    val temperature : Int,      // 온도
    val humidity : Float        // 습도
)
