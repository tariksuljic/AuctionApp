import { rules } from "src/forms/rules";

export const productDetailsFormFields = (categories, subcategories, setSelectedCategory) => [
  {
    label: "What do you sell?",
    type: "text",
    name: "productName",
    rules: {
      ...rules.required("Product name"),
      ...rules.minLength(2, "Product name"),
      ...rules.maxLength(100, "Product name")
    }
  },
  {
    label: "Category",
    type: "select",
    name: "category",
    options: categories,
    specialClass: "input-field-half",
    onSelectChange: setSelectedCategory,
    rules: {
      ...rules.required("Category")
    }
  },
  {
    label: "Subcategory",
    type: "select",
    name: "subcategory",
    options: subcategories,
    specialClass: "input-field-half",
    rules: {
      ...rules.required("Subcategory")
    }
  },
  {
    label: "Description",
    type: "textarea",
    name: "description",
    rules: {
      ...rules.required("Description"),
      ...rules.minLength(10, "Description"),
      ...rules.maxLength(700, "Description")
    }
  },
  {
    label: "Files",
    type: "file",
    name: "file",
    rules: {
      ...rules.required("File"),
      ...rules.minimumFiles(3)
    }
  }
];
