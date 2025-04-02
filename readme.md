# 1강
- [커밋](https://github.com/jhs512/kp-2025-04-02/commit/75dacd5)
- 프로젝트 생성

# 2강
- [커밋](https://github.com/jhs512/kp-2025-04-02/commit/855f31a)
- 엘라스틱 서치(노리 애널라이저 활성화 버전)용 docker-compose.yml 생성
- 엘라스틱 서치 API 주소 : http://localhost:9200

# 3강
- [커밋](https://github.com/jhs512/kp-2025-04-02/commit/435d7cf)
- PostDoc, PostDocRepository, PostDocService 생성
- BaseInitData 생성
  - PostDoc 인덱스 재생성
  - 샘플 글 6개 추가
- http://localhost:9200/_cat/indices?v
  - 모든 인덱스 출력
- http://localhost:9200/app1_posts
  - 인덱스 정보 출력
- http://localhost:9200/app1_posts/_settings
  - 인덱스 설정 정보 출력
- http://localhost:9200/app1_posts/_mapping
  - 인덱스 매핑 정보 출력
- http://localhost:9200/app1_posts/_search
  - 인덱스 검색

# 4강

## 제목기준으로 `대학` 이라는 키워드로 검색

## POSTMAN : ela_1/posts/_search
```
POST http://localhost:9200/app1_posts/_search
{
  "query": {
    "match": {
      "title": "대학"
    }
  }
}
```

# 5강
- [커밋](https://github.com/jhs512/kp-2025-04-02/commit/main)
- com.back.domain.post.post.entity 을(를) com.back.domain.post.post.document 으로 패키지명 변경
- 애널라이저 = 토크나이저 + 필터들
- 현재 nori 라는 글로벌 애널라이저가 있었다.
- korean 이라는 app1_posts 인덱스 전용 애널라이저를 생성
  - korean 애널라이저는 nori 애널라이저와 100% 동일하고 거기에 신조어를 학습했다.
  - 신조어는 infra/ela_1/user_dict.txt 파일에 정의