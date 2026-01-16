package com.innotree.rap.demo.views;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.part.ViewPart;

public class DemoFormView extends ViewPart{
	
	public static final String ID = "com.innotree.rap.demo.views.form";
	
	private Text firstNameText;
	private Text lastNameText;
	private Text emailText;
	private Label statusLabel;
	

	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		
		// FormToolkit 생성
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		
		// ScrolledForm 생성
		ScrolledForm form = toolkit.createScrolledForm(composite);
		form.getBody().setLayout(new TableWrapLayout());
		toolkit.decorateFormHeading(form.getForm());
		form.setText("User Registration Form");
		
		//Section 1: 사용자 정보 입력
		createUserInfoSection(toolkit, form);
		
		//Section 2: 상태표시
		createStatusSection(toolkit, form);
				
		
	}

	private void createUserInfoSection(FormToolkit toolkit, ScrolledForm form) {
		
		/*
		 * 이 Section은 제목과 설명을 가진다. 사용자는 이를 접거나 펼칠 수 있으며, 처음 화면이 열릴 때는 펼쳐진 상태로 보여준다.
		 */
		int sectionStyle = Section.TITLE_BAR | Section.DESCRIPTION | Section.TWISTIE | Section.EXPANDED;
		
		
		/*
		 * ScrolledForm form = toolkit.createScrolledForm(parent);
			이 객체는 내부적으로 다음과 같은 구조를 가집니다.
			
			ScrolledForm
			 └─ Form
			     ├─ Heading (제목 / 설명 / 툴바 / 메시지)
			     └─ Body (본문 컨텐츠 영역)
			     
			Form 자체는 컨테이너처럼 보이지만
			실제로 자식 컨트롤을 직접 추가하는 대상은 Body입니다.
		 */
		Composite body = form.getBody(); 
		Section section = toolkit.createSection(body, sectionStyle);
		
		section.setText("User Information");
		section.setDescription("Enter your personal information below");
		
		TableWrapData tableData = new TableWrapData(TableWrapData.FILL_GRAB);
		tableData.grabHorizontal = true;
		section.setLayoutData(tableData);
		
		// Section 내용
		Composite content = toolkit.createComposite(section);
		content.setLayout(new GridLayout(2, false));
		
		// First Name
		createMarkupLabel(content, "<b>First Name:</b>", toolkit);
		
		firstNameText = toolkit.createText(content, "", SWT.BORDER);
		firstNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		
		// Last Name
		createMarkupLabel(content, "<b>Last Name:</b>", toolkit);
		
		lastNameText = toolkit.createText(content, "", SWT.BORDER);
		lastNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		
		// Email
		
		createMarkupLabel(content,"<b>Email:</b>", toolkit);
		
		
		emailText = toolkit.createText(content, "", SWT.BORDER);
		emailText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		// Submit 버튼
		toolkit.createLabel(content, ""); // 빈 셀
		Button submitButton = toolkit.createButton(content, "Submit", SWT.PUSH);
		submitButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		submitButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleSubmit();
			}
		});
		
		section.setClient(content);
		
	}
	
	// 마크업 Label 생성 헬퍼 메서드
    private Label createMarkupLabel(Composite parent, String text, FormToolkit toolkit) {
        Label label = new Label(parent, SWT.NONE);
        label.setData(RWT.MARKUP_ENABLED, Boolean.TRUE);  // 생성 직후 설정!
        label.setText(text);
        toolkit.adapt(label, true, true);  // FormToolkit 스타일 적용
        return label;
    }
	
	private void createStatusSection(FormToolkit toolkit, ScrolledForm form) {
		
		// Status Section의 스타일을 설정한다. (타이들바가 있고 접기/펼치기 버튼이 있고 펼쳐저 있는 상태)
		int sectionStyle = Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED;
		
		// Status Section을 Toolkit으로부터 만든다
		Section section = toolkit.createSection(form.getBody(), sectionStyle);
		section.setText("Status");
		
		// Layout 속성 데이터를 TableWrapData의 기본값으로 사용한다. 
		TableWrapData tableData = new TableWrapData(TableWrapData.FILL_GRAB);
		section.setLayoutData(tableData);
		
		// Section으로부터 위젯을 담을 Composite를 tookit의 createXXX메소드를 사용하여 생성한다. 
		Composite content = toolkit.createComposite(section);
		// content Composite의 레이아웃을 GridLayout으로 설정한다. 
		content.setLayout(new GridLayout(1, false));
		
		// Label 위젯을 생성한다. 
		statusLabel = createMarkupLabel(content, "Ready to submit...", toolkit);
		// 부모(GridLayout)에 가로 공간에 여유공간이 생기면, 그 여유를 이 컨트롤이 가져가서 사용하도록 허용한다. 
		statusLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		section.setClient(content);
	}

	@Override
	public void setFocus() {
		
		if(firstNameText != null && !firstNameText.isDisposed()) {
			firstNameText.setFocus();
		}
	}
	
	private void handleSubmit() {
		String firstName = firstNameText.getText().trim();
		String lastName = lastNameText.getText().trim();
		String email = emailText.getText().trim();
		
		if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
			statusLabel.setText("<span style='color:red;'><b>Error:</b> All fields are required!</span>");
		} else {
			statusLabel.setText("<span style='color:green;'><b>Success:</b> User "
	                  + firstName + " " + lastName + " registered!</span>");
		}
		
	}

}
