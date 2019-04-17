myApp.controller("JobController", function($scope, $http, $location,
		$rootScope, $cookieStore) {

	$scope.jobDetail;
	$scope.editJobInfo;
	$scope.job = {
		'jobDescription' : '',
		'jobDesignation' : '',
		'jobProfile' : '',
		'qualificationRequired' : '',
		'jobStatus' : '',
		'username' : ''
	};

	$scope.addJob = function() {
		$scope.job.username = $rootScope.currentUser.username;
		$scope.job.jobStatus = 'closed';
		$http.post(
				'http://localhost:' + location.port
						+ '/middleware/addJob', $scope.job).then(
				function(response) {
					console.log('Job added');
					$location.path('/jobList');
				});
	}

	$scope.showEditJob = function() {
		var urlText = window.location.href;
		var editJobId = urlText.substring(urlText.indexOf("=") + 1);
		$http.get(
				'http://localhost:' + location.port
						+ '/middleware/getJob/' + editJobId).then(
				function(response) {
					$scope.editJobInfo = response.data;
					delete $scope.editJobInfo.createdDate;
				});
	}

	$scope.updateJob = function() {
		$http.post(
				'http://localhost:' + location.port
						+ '/middleware/updateJob', $scope.editJobInfo)
				.then(function(response) {
					console.log("Job updated.");
					$location.path("/jobList");
				});
	}

	$scope.getJobList = function() {
		$http.get(
				'http://localhost:' + location.port
						+ '/middleware/getJobList').then(
				function(response) {
					$scope.jobList = response.data;
					console.log("Job list retrieved.");
				});
	}

	$scope.deleteJob = function(jobId) {
		$http.get(
				'http://localhost:' + location.port
						+ '/middleware/deleteJob/' + jobId).then(
				function(response) {
					console.log("Job deleted");
				});
	}

	$scope.showJob = function(jobId) {
		if (jobId == undefined)
			jobId = $cookieStore.get("showJobId");
		if ($cookieStore.get("showJobId") == undefined) {
			$cookieStore.put("showJobId", jobId);
		} else if ($cookieStore.get("showJobId") != jobId) {
			$cookieStore.put("showJobId", jobId);
		} else {
			$http.get(
					'http://localhost:' + location.port
							+ '/middleware/getJob/'
							+ $cookieStore.get("showJobId")).then(
					function(response) {
						$scope.jobDetail = response.data;
					});
		}
	}

	$scope.openJob = function(jobId) {
		$http.get(
				'http://localhost:' + location.port
						+ '/middleware/openJob/' + jobId).then(
				function(response) {
					console.log("Job opened");
				});
	};

	$scope.closeJob = function(jobId) {
		$http.get(
				'http://localhost:' + location.port
						+ '/middleware/closeJob/' + jobId).then(
				function(response) {
					console.log("Job closed");
				});
	};

});

function jobMenuSwitch(tabId) {
	var tabPaneAllJobs = document.getElementById("tabPane-AllJobs");
	var tabPaneYourJobs = document.getElementById("tabPane-YourJobs");
	switch (tabId) {
	case "tab-AllJobs":
		tabPaneYourJobs.style.display = "none";
		tabPaneAllJobs.style.display = "";
		break;
	case "tab-YourJobs":
		tabPaneAllJobs.style.display = "none";
		tabPaneYourJobs.style.display = "";
		break;
	}
}