# 변경 이력 (Changelog)

## 2026-01-20

### ChartView 추가 (1ff39ac)
- `DemoChartView` 클래스 추가 - 차트 시각화 기능
- `plugin.xml`에 ChartView 등록
- Perspective 레이아웃 비율 조정
- ChartView를 우측 패널에 FormView와 함께 배치

### BrowserView 추가 (899396f)
- `DemoBrowserView` 클래스 추가 - 웹 콘텐츠 표시
- `TreeNode`에 URL 속성 추가
- 샘플 데이터를 실제 웹사이트 URL로 업데이트
- `MANIFEST.MF`에 `org.eclipse.core.runtime` 의존성 추가

### Background Job 기능 추가 (d4a091e)
- `JobAction` 클래스 추가 - 백그라운드 작업 실행
- `JobActionWithDialog` 클래스 추가 - 프로그레스 다이얼로그와 함께 작업 실행
- `ProgressView` 추가 - 작업 진행 상태 모니터링
- 액션 아이콘 추가 (sample.gif, progress_ok.gif, info.gif 등)
- ActionBar에 Exit, About 아이콘 지원 추가

### Editor 기능 추가 (2cb7f94, 3d244ef, 2cfb844)
- `DemoEditor` 클래스 추가 - 데모 파일 편집 기능
- `DemoEditorInput` 클래스 추가 - 에디터 입력 처리
- `plugin.xml`에 Editor 확장점 등록
- File 메뉴에 New Editor, Save, Save All 액션 추가
- 툴바에 에디터 관련 액션 추가

### ActionSet 기능 추가 (d7fe8b9)
- `SampleAction` 클래스 추가 - 액션 기여 데모
- `plugin.xml`에 ActionSet 확장점 등록

### Perspective 및 Window 설정 변경 (e80f6c7)
- Editor 영역 표시 활성화
- View 레이아웃 비율 조정
- 시작 시 윈도우 최대화 설정
- 고정 초기 윈도우 크기 제거

### Launch 설정 업데이트 (2d3f0a6)
- 실행 시 워크스페이스 클리어 활성화
- 미사용 `rap.design.example` 번들 제거

## 2026-01-16

### 프로젝트 문서화 (b65996d)
- `README.md` 추가 - 프로젝트 설명 및 실행 가이드
- 스크린샷 추가

### 초기 프로젝트 구성 (2f56dad)
- Eclipse RAP 데모 프로젝트 초기 커밋
- 기본 Workbench 구조 (DemoWorkbench, Advisor 클래스들)
- 4개의 View 구현 (Tree, Table, Form, Selection)
- Perspective 정의
