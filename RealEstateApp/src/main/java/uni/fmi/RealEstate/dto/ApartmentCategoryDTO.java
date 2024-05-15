package uni.fmi.RealEstate.dto;

import lombok.NoArgsConstructor;
import uni.fmi.RealEstate.models.ApartmentCategory;

import java.util.List;

@NoArgsConstructor
public class ApartmentCategoryDTO extends BaseDTO {
    private String name;
    private ApartmentCategoryDTO parent;
    private List<ApartmentCategoryDTO> children;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ApartmentCategoryDTO getParent() { return parent; }
    public void setParent(ApartmentCategoryDTO parent) { this.parent = parent; }
    public List<ApartmentCategoryDTO> getChildren() { return children; }
    public void setChildren(List<ApartmentCategoryDTO> children) { this.children = children; }
}
