myApp.controller("FriendController", function($scope, $http, $location,
		$rootScope) {
	$scope.friendList;
	$scope.pendingFriendList;
	$scope.suggestedFriendList;

	// retrieving friend list
	$http.get(
			'http://localhost:' + location.port
					+ '/middleware/listFriends/'
					+ $rootScope.currentUser.username).then(function(response) {
		$scope.friendList = response.data;
		console.log("Friend list retrieved");
	});

	// retrieving friend requests
	$http.get(
			'http://localhost:' + location.port
					+ '/middleware/listPendingFriends/'
					+ $rootScope.currentUser.username).then(function(response) {
		$scope.pendingFriendList = response.data;
		console.log("Friend requests retrieved");
	});

	// retrieving suggested friend list
	$http.get(
			'http://localhost:' + location.port
					+ '/middleware/listSuggestedFriends/'
					+ $rootScope.currentUser.username).then(function(response) {
		$scope.suggestedFriendList = response.data;
		console.log("Suggested friend list retrieved");
	});

	$scope.sendFriendReq = function(friendUsername) {
		var newFriendReq = {
			"username" : $rootScope.currentUser.username,
			"friendUsername" : friendUsername,
			"status" : "P"
		};
		$http.post(
				'http://localhost:' + location.port
						+ '/middleware/sendFriendReq/', newFriendReq)
				.then(function(response) {
					console.log("Friend request sent");
				});
	};

	$scope.acceptFriendReq = function(friendReqId) {
		$http.get(
				'http://localhost:' + location.port
						+ '/middleware/acceptFriendReq/' + friendReqId)
				.then(function(response) {
					console.log("Friend request accepted");
				});
	};

	$scope.rejectFriendReq = function(friendReqId) {
		$http.get(
				'http://localhost:' + location.port
						+ '/middleware/rejectFriendReq/' + friendReqId)
				.then(function(response) {
					console.log("Friend request rejected");
				});
	};

	$scope.unfriend = function(friendUsername) {
		$http.get(
				'http://localhost:' + location.port
						+ '/middleware/unfriend/'
						+ $rootScope.currentUser.username + '/'
						+ friendUsername).then(function(response) {
			console.log("Unfriended");
		});
	};

});