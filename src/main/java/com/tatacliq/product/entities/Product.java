package com.tatacliq.product.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@NotNull(message = "sellerId cannot be null")
	private String sellerId;

	@NotNull(message = "title cannot be null")
	private String title;

	@NotNull(message = "manufacturer cannot be null")
	private String manufacturer;

	@NotNull(message = "isLowQuality cannot be null")
	private Boolean isLowQuantity;

	@NotNull(message = "isSoldOut cannot be null")
	private Boolean isSoldOut;

	@NotNull(message = "isBackOrder cannot be null")
	private Boolean isBackorder;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "metafieldId")
	private List<MetaField> metafields;

	@NotNull(message = "requiresShipping cannot be null")
	private Boolean requiresShipping;

	@NotNull(message = "isVisible cannot be null")
	private Boolean isVisible;

	@NotNull(message = "publishedAt cannot be null")
	@Temporal(TemporalType.TIMESTAMP)
	private Date publishedAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@NotNull(message = "workflowStatus cannot be null")
	private String workflowStatus;

	@OneToOne(cascade = {CascadeType.ALL})
	@PrimaryKeyJoinColumn
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ProductPrice productPrice;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	

	public Boolean getIsLowQuantity() {
		return isLowQuantity;
	}

	public void setIsLowQuantity(Boolean isLowQuantity) {
		this.isLowQuantity = isLowQuantity;
	}

	public Boolean getIsSoldOut() {
		return isSoldOut;
	}

	public void setIsSoldOut(Boolean isSoldOut) {
		this.isSoldOut = isSoldOut;
	}

	public Boolean getIsBackorder() {
		return isBackorder;
	}

	public void setIsBackorder(Boolean isBackorder) {
		this.isBackorder = isBackorder;
	}

	public List<MetaField> getMetafields() {
		return metafields;
	}

	public void setMetafields(List<MetaField> metafields) {
		this.metafields = metafields;
	}

	public Boolean getRequiresShipping() {
		return requiresShipping;
	}

	public void setRequiresShipping(Boolean requiresShipping) {
		this.requiresShipping = requiresShipping;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getWorkflowStatus() {
		return workflowStatus;
	}

	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	public ProductPrice getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(ProductPrice productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", sellerId=" + sellerId + ", title=" + title + ", manufacturer="
				+ manufacturer + ", isLowQuantity=" + isLowQuantity + ", isSoldOut=" + isSoldOut + ", isBackorder="
				+ isBackorder + ", metafields=" + metafields + ", requiresShipping=" + requiresShipping + ", isVisible="
				+ isVisible + ", publishedAt=" + publishedAt + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", workflowStatus=" + workflowStatus + ", productPrice=" + productPrice + "]";
	}
	
	

}
