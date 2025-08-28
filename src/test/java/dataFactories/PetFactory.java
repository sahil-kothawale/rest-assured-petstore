package dataFactories;

import java.util.Random;
import models.request.Category;
import models.request.Tag;
import models.request.PetRequest;

public class PetFactory {

	public static PetRequest createRandomPet() {
		PetRequest request = new PetRequest();
		request.setId(new Random().nextInt(100));
		request.setName("Pet" + request.getId());
		request.setStatus("available");
		request.setPhotoUrls(new String[] {"abc/123", "xyz/234"});
		request.setCategory(new Category(new Random().nextInt(10), "Dog"));
		request.setTags(new Tag[] {new Tag(new Random().nextInt(10), "ABC23456")});
		return request;
	}
	
}
