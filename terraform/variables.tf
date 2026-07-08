variable "resource_group_name" {
  type        = string
  description = "The name of the Resource Group in which to create the resources."
  default     = "foodhub-devops-rg"
}

variable "location" {
  type        = string
  description = "The Azure region where all resources should be created."
  default     = "East US"
}

variable "vnet_name" {
  type        = string
  description = "The name of the Virtual Network."
  default     = "foodhub-vnet"
}

variable "subnet_name" {
  type        = string
  description = "The name of the Subnet within the VNet."
  default     = "foodhub-subnet"
}

variable "acr_name" {
  type        = string
  description = "The globally unique name of the Azure Container Registry. Lowercase alphanumeric only."
  default     = "foodhubregistrydevops"
}

variable "aks_cluster_name" {
  type        = string
  description = "The name of the Azure Kubernetes Service cluster."
  default     = "foodhub-aks"
}

variable "dns_prefix" {
  type        = string
  description = "DNS prefix specified when creating the managed Kubernetes cluster."
  default     = "foodhubaks"
}

variable "node_count" {
  type        = number
  description = "The number of nodes in the default node pool for the AKS cluster."
  default     = 2
}

variable "vm_size" {
  type        = string
  description = "The size of the Virtual Machines to use for the AKS nodes."
  default     = "Standard_DS2_v2"
}
