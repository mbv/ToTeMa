angular
    .module('demo')
    .controller('EntityListController', EntityListController);

function EntityListController($resource, $uibModal, resourceName, self, toaster) {
    self.resourceName = resourceName;
    self.EntityService = $resource('api/' + self.resourceName + '/:id',
        {
            id: '@id'
        },
        {
            update: {
                method: 'PUT',
            }

        });

    self.entities = [];
    self.isUpdatingChosen = false;

    self.addEntity = addEntity;
    self.deleteEntity = deleteEntity;
    self.editEntity = editEntity;

    refresh();

    function refresh() {
        self.EntityService.query().$promise.then(function (result) {
            console.log('An error success:');
            self.entities = result;
        }, function(errorMsg) {
            // if any of the previous promises gets rejected
            // the success callback will never be executed
            // the error callback will be called...
            toaster.pop('error', "GOPA", "POLNAYA");
            console.log('An error occurred: ', errorMsg);
        });
    }

    function addEntity() {
        openModal();
    }

    function deleteEntity(entity) {
        self.EntityService.delete(entity, refresh);
    }

    function editEntity(entity) {
        openModal(entity);
    }

    function openModal(entity) {
        var modalInstance = $uibModal.open({
            templateUrl: self.resourceName + 'Modal.html',
            controller: 'EntityModalController as self',
            resolve: {
                entity: function () {
                    return angular.copy(entity);
                },
                EntityService: function () {
                    return self.EntityService
                }
            }
        });
        modalInstance.result.then(refresh);
    }
}


