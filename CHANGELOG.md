# 변경 이력 (Changelog)

## 2026-01-20

### Editor 기능 추가
- `DemoEditor` 클래스 추가 - 데모 파일 편집 기능
- `DemoEditorInput` 클래스 추가 - 에디터 입력 처리
- `plugin.xml`에 Editor 확장점 등록

### ActionSet 기능 추가
- `SampleAction` 클래스 추가 - 액션 기여 데모
- `plugin.xml`에 ActionSet 확장점 등록

### ActionBar 업데이트
- File 메뉴에 New Editor, Save, Save All 액션 추가
- 툴바에 에디터 관련 액션 추가

### Perspective 및 Window 설정 변경
- Editor 영역 표시 활성화
- View 레이아웃 비율 조정
- 시작 시 윈도우 최대화 설정
- 고정 초기 윈도우 크기 제거

### Launch 설정 업데이트
- 실행 시 워크스페이스 클리어 활성화
- 미사용 `rap.design.example` 번들 제거

## 2026-01-16

### 프로젝트 문서화
- `README.md` 추가 - 프로젝트 설명 및 실행 가이드
- 스크린샷 추가

### 초기 프로젝트 구성
- Eclipse RAP 데모 프로젝트 초기 커밋
- 기본 Workbench 구조 (DemoWorkbench, Advisor 클래스들)
- 4개의 View 구현 (Tree, Table, Form, Selection)
- Perspective 정의
