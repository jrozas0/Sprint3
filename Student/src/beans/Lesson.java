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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String description;

	//bi-directional many-to-one association to Section
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="SECTION_id", nullable=false)
	private Section section;

	//bi-directional many-to-one association to Material
	@ManyToOne
	@JoinColumn(name="MATERIAL_id", nullable=false)
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