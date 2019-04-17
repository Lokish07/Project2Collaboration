myApp.controller("UserController", function($scope, $http, $location, $rootScope, $cookieStore) {

	$scope.profilePic;
	$scope.removeProfilePicUrl;

	if ($rootScope.currentUser) {
		// setting the url of the current users profile picture
		$scope.profilePicUrl = "http://localhost:" + location.port + "/middleware/showProfilePic/" + $rootScope.currentUser.username;

		// retrieving the profile picture
		$http.get('http://localhost:' + location.port + '/middleware/getProfilePic/' + $rootScope.currentUser.username).then(function(response) {
			$scope.profilePic = response.data;
			if ($scope.profilePic)
				$scope.removeProfilePicUrl = "http://localhost:" + location.port + "/middleware/deleteProfilePic/" + $rootScope.currentUser.username;
		});
	}

	$scope.user = {
		'username' : '',
		'password' : '',
		'firstName' : '',
		'lastName' : '',
		'email' : '',
		'role' : '',
		'status' : '',
		'isOnline' : ''
	};

	$scope.register = function() {
		$scope.user.role = 'Student';
		$scope.user.status = 'A';
		$scope.user.isOnline = 'Off';
		$http.post('http://localhost:' + location.port + '/middleware/registerUser', $scope.user).then(function(response) {
			console.log('User registered');
			$location.path('/login');
		})
	};

	$scope.loginCheck = function() {
		$http.post('http://localhost:' + location.port + '/middleware/checkLogin', $scope.user).then(function(response) {
			console.log('login successfull');
			$http.get('http://localhost:' + location.port + '/middleware/getUser/' + $scope.user.username).then(function(response) {
				$rootScope.currentUser = response.data;
				$cookieStore.put('userDetails', $rootScope.currentUser);
				window.location.href = "http://localhost:" + location.port + "/front/index2.html#/userHome";
			});
		});
	};

	$scope.logout = function() {
		delete $rootScope.currentUser;
		$cookieStore.remove('userDetails');
		console.log("logout successful.");
		window.location.href = "http://localhost:" + location.port + "/front/";
	};

});