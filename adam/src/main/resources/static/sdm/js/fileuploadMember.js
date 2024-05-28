let selectedFile = null;

document.getElementById('uploadFile').addEventListener('change', function(event) {
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

                // 이미지 컨테이너에 이미지, 라디오 버튼, 닫기 버튼, 파일명 추가
                imageContainer.appendChild(imagePreview);

                // 이미지 컨테이너를 래퍼에 추가
                imageContainerWrapper.appendChild(imageContainer);
            };

            reader.readAsDataURL(file); // 파일을 DataURL로 읽음
        } else {
            alert("이미지 파일을 선택해 주세요");
        }
    });
});