# UrlShortener
원본 URL을 짧게 단축시키는 시스템
### Use Skill tree
| | Skills |
|---|---|
| <b>Back-End</b> | Spring Framework(MVC, JPA) |
| <b>Front-End</b> | Thyme-leaf |
| <b>Infra</b> | Linux, MariaDB |
### 주요 기능
1. 긴 원본 URL을 짧은 형태나 사용자가 희망하는 단어의 형태로 URL 단축
2.  단축된 URL로 접속 시도 시, (존재하는 URL의 경우) 원본 URL로 Redirect 처리
3. 정상적인 HTTP(HTTPS) URL인지 검증한 후 단축 진행
4. 단축 URL을 특정 기간에만 유지되도록 하고, 특정 기간을 경과하면 접속 차단<br>
### 구현 현황
- [x] 개발 환경 구축
- [x] DB 설계 및 Entity 구현
- [x] HTTP(HTTPS) URL 검증 구현
- [x] URL 단축 알고리즘 구현
- [x] URL 단축 저장 및 조회 기능 구현
- [ ] 테스트 및 배포