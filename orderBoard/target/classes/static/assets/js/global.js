document.getElementById('header').innerHTML = `
<a href=""><div class="header-left">Restaurant Reservation</div></a>
<div class="header-center">Welcome，{{ user.yhm }}</div>
<div class="header-right">
    <el-dropdown placement="bottom">
        <div class="avatar-box"><img :src="user.tx"></div>
        <el-dropdown-menu slot="dropdown">
            <div onclick="goToPerson()"><el-dropdown-item icon="el-icon-user">Personal</el-dropdown-item></div>
            <div onclick="logout()"><el-dropdown-item icon="el-icon-back">Logout</el-dropdown-item></div>
        </el-dropdown-menu>
    </el-dropdown>
</div>
`


document.getElementById('main-menu').innerHTML = `

<div class="main-nav">
    <el-menu :default-active="activeIndex" class="main-nav-menu" @select="selectMenu">
		<el-submenu index="1" v-if="authority.includes(1000)">
			<template slot="title">
				<i class="el-icon-s-home"></i>
				<span>Index</span>
			</template>
			<el-menu-item v-if="authority.includes(1001)" index="index.html">Index</el-menu-item>
		</el-submenu>
		
		<el-submenu index="2" v-if="authority.includes(2000)">
			<template slot="title">
				<i class="el-icon-user-solid"></i>
				<span>User Manage</span>
			</template>
			<el-menu-item v-if="authority.includes(1)" index="gly.html">Admin</el-menu-item>
			<el-menu-item v-if="authority.includes(2)" index="user.html">User</el-menu-item>
		</el-submenu>
		<el-submenu index="3" v-if="authority.includes(3000)">
			<template slot="title">
				<i class="el-icon-menu"></i>
				<span>Info Manage</span>
			</template>
			<el-menu-item v-if="authority.includes(3)" index="boardmanage.html">BoardManage</el-menu-item>
			<el-menu-item v-if="authority.includes(4)" index="boardorder.html">BoardOrder</el-menu-item>
		</el-submenu>
		<el-submenu index="4" v-if="authority.includes(4000)">
			<template slot="title">
				<i class="el-icon-s-tools"></i>
				<span>Sys Manage</span>
			</template>
			<el-menu-item v-if="authority.includes(4001)" index="password.html">UpdatePassword</el-menu-item>
			<el-menu-item v-if="authority.includes(4002)" onclick="logout()">Logout</el-menu-item>
		</el-submenu>
    </el-menu>
</div>

`

// Logout
function logout() {
    axios.get("/logout").then(res => {
        localStorage.setItem('user', null);
        location.href = "/end/login.html";
    })
}

// 跳转到个人中心页面
function goToPerson() {
    let user = JSON.parse(localStorage.getItem('user'));
	if (user.role === 1) {
		location.href = '/end/pGly.html';
	}
	if (user.role === 2) {
		location.href = '/end/pUser.html';
	}

}

