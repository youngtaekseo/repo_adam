//let selectedFile = null;

// API 엔드포인트에서 이미지 데이터를 가져오는 비동기 함수
async function fetchImages() {
    try {
        // /api/images 엔드포인트에서 데이터를 가져옴 
        let pdtSeq = $("#pdtSeq").val();
        const response = await fetch('/productLoadImage?pdtSeq='+pdtSeq);
        // JSON 형식으로 응답 데이터를 파싱
        const images = await response.json();
        return images;
    } catch (error) {
        // 데이터를 가져오는 중 에러가 발생하면 콘솔에 출력
        console.error('Error fetching images:', error);
    }
}

// 이미지 갤러리를 동적으로 생성하는 비동기 함수
async function createImageGallery() {
	//저장소구분
	const uploadType = document.getElementById('uploadType');
    // 이미지 갤러리 컨테이너 요소를 가져옴
    const gallery = document.getElementById('imageContainerWrapper');
    // 이미지 데이터를 가져옴
    const images = await fetchImages();
    // 각 이미지 데이터에 대해 반복
    images.forEach(image => {
        // 새로운 div 요소를 생성하여 이미지 컨테이너로 사용
        const imageContainer = document.createElement('div');
        imageContainer.classList.add('image-container');

        // 새로운 img 요소를 생성하여 이미지 추가
        const img = document.createElement('img');
        if(uploadType.value == '0') { // aws
			img.src = image.xpathUpload;
		} else {
			let src = "data:image/"+image.xext.toLowerCase()+";base64," + image.xpathUpload;
			img.src = src;
		}
        
       // 라디오 버튼 생성
        let radioButton = document.createElement('input');
        radioButton.type = 'radio';
        radioButton.name = 'imageRadioLoad';
        radioButton.value = image.xfileName;
        radioButton.className = 'custom-radio';
		radioButton.disabled = true;

		if(image.xdefaultNy == "0") {
			radioButton.checked = true;
		}

        // 파일명 요소 생성
        let fileName = document.createElement('div');
        fileName.className = 'file-name';
        fileName.textContent = image.xfileName;           

        // 이미지 컨테이너에 이미지, 라디오 버튼, 닫기 버튼, 파일명 추가
        imageContainer.appendChild(img);
        
        if(image.xdefaultNy == "0") {
        	imageContainer.appendChild(radioButton);
        }
        
        imageContainer.appendChild(fileName);        
        // 갤러리에 이미지 컨테이너를 추가
        gallery.appendChild(imageContainer);
    });
}

// 페이지가 로드될 때 이미지 갤러리를 생성하도록 설정
window.onload = createImageGallery;