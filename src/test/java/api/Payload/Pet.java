package api.Payload;

public class Pet 
{
	String id;
	String name;
	String category[]= {id,name};
	String photoUrls;
	String tags[]= {id,name};
	String status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getCategory() {
		return category;
	}
	public void setCategory(String[] category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(String photoUrls) {
		this.photoUrls = photoUrls;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	  
		

}
