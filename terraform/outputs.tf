output "resource_group_name" {
  value       = azurerm_resource_group.rg.name
  description = "The name of the resource group."
}

output "acr_name" {
  value       = azurerm_container_registry.acr.name
  description = "The name of the Container Registry."
}

output "acr_login_server" {
  value       = azurerm_container_registry.acr.login_server
  description = "The URL of the Container Registry login server."
}

output "aks_cluster_name" {
  value       = azurerm_kubernetes_cluster.aks.name
  description = "The name of the Kubernetes cluster."
}

output "aks_api_server" {
  value       = azurerm_kubernetes_cluster.aks.fqdn
  description = "The FQDN of the AKS API server."
}

output "connect_command" {
  value       = "az aks get-credentials --resource-group ${azurerm_resource_group.rg.name} --name ${azurerm_kubernetes_cluster.aks.name}"
  description = "Command to connect kubectl to the created AKS cluster."
}
