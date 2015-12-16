package beans;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the LESSON database table.
 * 
 */
@Entity
@Table(name="LESSON")
@NamedQuery(name="Lesson.findAll", query="SELECT l FROM Lesson l")
public class Lesson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String description;

	//bi-directional many-to-one association to Section
	@ManyToOne
	@JoinColumn(name="SECTION_id")
	private Section section;

	//bi-directional many-to-one association to Material
	@ManyToOne
	@JoinColumn(name="MATERIAL_id")
	private Material material;

	public Lesson() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

}