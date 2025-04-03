package com.back.global.initData

import com.back.domain.post.post.service.PostDocService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy

@Configuration
class BaseInitData(
    private val postDocService: PostDocService
) {
    @Lazy
    @Autowired
    lateinit var self: BaseInitData

    @Bean
    fun baseInitDataApplicationRunner(): ApplicationRunner {
        return ApplicationRunner {
            self.work1()
        }
    }

    fun work1() {
        if (postDocService.count() > 0) return
        postDocService.deleteIndex()
        postDocService.createIndex()

        postDocService.add("title1", "content1")
        postDocService.add("title2", "content2")
        postDocService.add("title3", "content3")

        postDocService.add(
            "저는 홍길동이고 올해로 21살 입니다. 잘 부탁드립니다.", """
                        저는 서울에서 태어나 자랐습니다. 현재는 대학생으로, 컴퓨터 공학을 전공하고 있습니다.
                        프로그래밍에 큰 관심이 있으며, 다양한 프로젝트에 참여하고 있습니다.
                        앞으로도 많은 도움 부탁드립니다.
        """.trimIndent()
        )

        postDocService.add(
            "안녕하세요, 제 이름은 이지은입니다. 올해 스물 다섯살이 되었습니다.",
            """
        어릴 적부터 음악과 예술에 관심이 많았고, 현재는 디자인을 전공하고 있습니다.
        다양한 문화 콘텐츠를 즐기며 새로운 시각을 배우는 걸 좋아합니다.
        잘 부탁드립니다.
    """.trimIndent()
        )

        postDocService.add(
            "반갑습니다. 저는 김도윤이고, 30살입니다.",
            """
        IT 업계에서 백엔드 개발자로 일하고 있습니다.
        자바와 스프링 프레임워크에 익숙하며, 클린 코드 작성을 중요하게 생각합니다.
        좋은 인연이 되었으면 좋겠습니다.
    """.trimIndent()
        )

        postDocService.add(
            "안녕하세요. 저는 최유진입니다. 27살이에요.",
            """
        여행을 즐기고, 새로운 사람들과의 만남을 좋아합니다.
        현재는 프론트엔드 개발을 배우고 있고, 리액트와 타입스크립트를 공부하고 있습니다.
        함께 성장할 수 있었으면 좋겠습니다.
    """.trimIndent()
        )

        postDocService.add(
            "안녕하십니까. 제 이름은 박시우입니다. 올해 나이는 33살입니다.",
            """
        이전에는 마케팅 분야에서 일했지만, 최근에 프로그래밍에 흥미를 느껴 커리어를 전환하고 있습니다.
        특히 데이터 분석과 AI에 관심이 많습니다.
        좋은 정보 많이 나누고 싶습니다.
    """.trimIndent()
        )

        postDocService.add(
            "저는 정예린이고, 22살 대학생입니다.",
            """
        심리학과에 재학 중이며, 사람의 마음과 행동에 대해 깊이 이해하고 싶습니다.
        최근에는 웹 개발도 함께 배우고 있으며, 다양한 기술을 접하는 중입니다.
        잘 부탁드립니다!
    """.trimIndent()
        )

        postDocService.add(
            "저는 김한솔이고, 10살 초등학생입니다.",
            """
        저는 밥도 잘먹고, 친구들과 사이좋게 지냅니다. 그리고 게임을 좋아합니다.
    """.trimIndent()
        )
    }
}