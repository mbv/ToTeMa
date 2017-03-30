angular
    .module('demo')
    .controller('ConversationsRateListController', ConversationsRateListController);

function ConversationsRateListController($controller) {
    var self = this;
    $controller('ConversationsRateListController', {resourceName: 'conversationsRates', self: self});
}
