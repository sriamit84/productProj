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

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	private String sellerId;

	private String title;

	private String manufacturer;

	private Boolean isLowQuantity;

	private Boolean isSoldOut;

	private Boolean isBackorder;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "metafieldId")
	private List<MetaField> metafields;

	private Boolean requiresShipping;

	private Boolean isVisible;

	@Temporal(TemporalType.TIMESTAMP)
	private Date publishedAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

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

}
