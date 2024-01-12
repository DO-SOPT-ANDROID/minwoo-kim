package org.sopt.dosopttemplate.data.model.local

import org.sopt.dosopttemplate.R

object DummyFriendsData {
    val friendList = mutableListOf(
        Friend.FriendMy(
            profileImage = R.drawable.img_jjang_gu,
            name = "김민우",
            description = "울라~울라~"
        ),
        Friend.FriendInfo(
            profileImage = R.drawable.img_jjang_gu,
            name = "김생호",
            description = "나는야~ 주정뱅이~~~",
            music = "임창정 - 소주 한 잔"
        ), Friend.FriendInfo(
            profileImage = R.drawable.img_jjang_gu,
            name = "이유빈",
            description = "안봉이의 최고 막내",
            music = null
        ),
        Friend.FriendBirthday(
            profileImage = R.drawable.img_jjang_gu,
            name = "강유리",
            description = "나는 막차타고 집에 가야돼",
            birthday = "10월 27일"
        ),
        Friend.FriendInfo(
            profileImage = R.drawable.img_jjang_gu,
            name = "박동민",
            description = "노란머리의 사나이",
            music = null
        ), Friend.FriendInfo(
            profileImage = R.drawable.img_jjang_gu,
            name = "경지현",
            description = "경자",
            music = null
        ),
        Friend.FriendInfo(
            profileImage = R.drawable.img_jjang_gu,
            name = "이태희",
            description = "오늘부터 아이폰으로 갈아탑니다.",
            music = null
        ),
        Friend.FriendInfo(
            profileImage = R.drawable.img_jjang_gu,
            name = "조관희",
            description = "코딩은 참아도 근손실은 못참아",
            music = "단백질송"
        ), Friend.FriendInfo(
            profileImage = R.drawable.img_model,
            name = "조세연",
            description = "나는 모델입니다.",
            music = "피의게임2 OST"
        )
    )
}