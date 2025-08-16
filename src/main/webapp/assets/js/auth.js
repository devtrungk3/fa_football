const password = document.getElementById('password');
const repassword = document.getElementById("rppassword");

document.getElementById('form').addEventListener('submit', function(event) {
	event.defaultPrevented();
	if (password === repassword) {
		alert("Mật khẩu không trùng khớp");
		return;
	}
	event.target.submit();
})