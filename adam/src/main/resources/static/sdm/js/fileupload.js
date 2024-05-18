let selectedFile = null;

document.getElementById('uploadFiles').addEventListener('change', function(event) {
    let files = Array.from(event.target.files); // 선택된 모든 파일
    let imageContainerWrapper = document.getElementById('imageContainerWrapper');

    // 이전에 추가된 모든 이미지 컨테이너 제거
    imageContainerWrapper.innerHTML = '';

    files.forEach((file, index) => {
        if (file && file.type.startsWith('image/')) {
            let reader = new FileReader();

            reader.onload = function(e) {
                // 이미지 컨테이너 생성
                let imageContainer = document.createElement('div');
                imageContainer.className = 'image-container';

                // 이미지 요소 생성
                let imagePreview = document.createElement('img');
                imagePreview.src = e.target.result;
                imagePreview.alt = file.name;

                // 라디오 버튼 생성
                let radioButton = document.createElement('input');
                radioButton.type = 'radio';
                radioButton.name = 'imageRadio';
                radioButton.value = file.name;
                radioButton.className = 'custom-radio';

                // 선택된 파일이 없는 경우 첫 번째 이미지의 라디오 버튼을 선택
                if (!selectedFile) {
                    selectedFile = file;
                    radioButton.checked = true;
                }

                // 닫기 버튼 생성
                let closeButton = document.createElement('button');
                closeButton.className = 'close-button';
                closeButton.innerHTML = '&times;';

                // 파일명 요소 생성
                let fileName = document.createElement('div');
                fileName.className = 'file-name';
                fileName.textContent = file.name;

                // 닫기 버튼 클릭 이벤트 리스너
                closeButton.addEventListener('click', function() {
                    // 이미지 컨테이너를 래퍼에서 제거
                    imageContainerWrapper.removeChild(imageContainer);
                    // 선택된 파일 목록에서 해당 파일 제거
                    files = files.filter(selectedFile => selectedFile !== file);
                    // 파일 입력 요소 초기화 및 재설정
                    const dataTransfer = new DataTransfer();
                    files.forEach(file => dataTransfer.items.add(file));
                    document.getElementById('uploadFiles').files = dataTransfer.files;

                    // 삭제 후 첫 번째 이미지의 라디오 버튼을 선택
                    let checkedRadio = document.querySelector('.custom-radio:checked');
                    if (!checkedRadio) {
                        document.querySelector('.custom-radio:first-of-type').checked = true;
                        selectedFile = document.querySelector('.custom-radio:first-of-type').value;
                    } else {
                        selectedFile = checkedRadio.value;
                    }
                });

                // 라디오 버튼 변경 이벤트 리스너
                radioButton.addEventListener('change', function() {
                    // 선택된 파일 변경
                    selectedFile = file;

                    // 모든 라디오 버튼의 가운데 색상 초기화
                    let allRadioButtons = document.querySelectorAll('.custom-radio');
                    allRadioButtons.forEach(button => {
                        button.previousElementSibling.style.backgroundColor = 'transparent';
                    });
                });

                // 이미지 컨테이너에 이미지, 라디오 버튼, 닫기 버튼, 파일명 추가
                imageContainer.appendChild(imagePreview);
                imageContainer.appendChild(radioButton);
                imageContainer.appendChild(closeButton);
                imageContainer.appendChild(fileName);

                // 이미지 컨테이너를 래퍼에 추가
                imageContainerWrapper.appendChild(imageContainer);
            };

            reader.readAsDataURL(file); // 파일을 DataURL로 읽음
        } else {
            alert("이미지 파일을 선택해 주세요");
        }
    });
});