// 페이지가 로드될 때 이미지 호출
window.onload = () => {
	let imageUrl = document.getElementById("imageUrl");
	location.href = "/viewFile?xfileName="+imageUrl.src;
}