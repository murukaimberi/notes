I'll walk you through creating a custom autocomplete select component using Angular and Bootstrap, explaining each stage in detail.

---

### **Approach**
We will build an Angular component that:
1. Accepts an input for searching.
2. Filters a predefined list based on the user's input.
3. Displays a dropdown of matching options.
4. Allows users to select an option, updating the input field.

---

### **Steps to Build the Autocomplete Select**
#### **1. Set Up the Component**
Create a new component for the autocomplete feature:
```sh
ng generate component autocomplete-select
```

#### **2. Define the Component Template (`autocomplete-select.component.html`)**
```html
<div class="position-relative">
  <input type="text" 
         class="form-control"
         [(ngModel)]="searchTerm" 
         (input)="filterOptions()"
         (focus)="showDropdown = true"
         (blur)="hideDropdownWithDelay()"
         placeholder="Select an option">

  <ul *ngIf="showDropdown && filteredOptions.length > 0" 
      class="dropdown-menu show w-100" 
      style="position: absolute; top: 100%;">
    <li *ngFor="let option of filteredOptions" 
        class="dropdown-item"
        (click)="selectOption(option)">
      {{ option }}
    </li>
  </ul>
</div>
```
**Explanation:**
- A Bootstrap-styled `<input>` field captures user input.
- A `<ul>` dropdown appears dynamically when the user types or focuses on the input.
- A click event on `<li>` elements allows selection.
- We use `position-relative` on the wrapper and `position: absolute` for the dropdown for proper alignment.

---

#### **3. Define Component Logic (`autocomplete-select.component.ts`)**
```typescript
import { Component } from '@angular/core';

@Component({
  selector: 'app-autocomplete-select',
  templateUrl: './autocomplete-select.component.html',
  styleUrls: ['./autocomplete-select.component.css']
})
export class AutocompleteSelectComponent {
  searchTerm: string = '';
  showDropdown: boolean = false;
  options: string[] = ['Apple', 'Banana', 'Cherry', 'Date', 'Grape', 'Mango', 'Orange'];
  filteredOptions: string[] = [];

  constructor() {
    this.filteredOptions = [...this.options]; // Initialize with full list
  }

  filterOptions() {
    if (!this.searchTerm) {
      this.filteredOptions = [...this.options];
    } else {
      this.filteredOptions = this.options.filter(option =>
        option.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    }
    this.showDropdown = this.filteredOptions.length > 0;
  }

  selectOption(option: string) {
    this.searchTerm = option;
    this.showDropdown = false;
  }

  hideDropdownWithDelay() {
    setTimeout(() => {
      this.showDropdown = false;
    }, 200); // Delay to allow click events to register
  }
}
```
**Explanation:**
1. The `searchTerm` binds to the input field to capture user input.
2. `filterOptions()` filters the list based on the search term.
3. `selectOption(option)` updates the input field when an option is selected.
4. `hideDropdownWithDelay()` prevents the dropdown from closing too quickly.

---

#### **4. Style the Component (`autocomplete-select.component.css`)**
```css
.dropdown-menu {
  max-height: 200px;
  overflow-y: auto;
}
```
**Explanation:** 
- Limits dropdown height to avoid overflow.
- Enables scrolling if there are too many options.

---

#### **5. Use the Component in Another Component**
Add the custom component to your app:
```html
<app-autocomplete-select></app-autocomplete-select>
```

---

### **Intuition Behind the Approach**
- **Why Bootstrap?**  
  - We use Bootstrap's `dropdown-menu` for styling, making it look like a native select.
- **Why Not Angular Material?**  
  - You requested an implementation without Angular Material.
- **Handling Edge Cases:**  
  - Prevents dropdown from closing instantly when clicking.
  - Ensures dropdown aligns properly.

---

### **Useful References**
- [Angular ngModel Docs](https://angular.io/api/forms/NgModel)
- [Bootstrap Dropdowns](https://getbootstrap.com/docs/5.3/components/dropdowns/)
- [Filtering Arrays in JavaScript](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/filter)


# Second solution with refinement

---

Got it! We'll modify the autocomplete to handle objects instead of simple strings. Here's the updated approach:  

---

## **Updated Approach**
Each option will be an object with properties like `id` and `name`. When a user selects an option, the full object will be stored instead of just the name.

### **1. Update the Component Template (`autocomplete-select.component.html`)**
```html
<div class="position-relative">
  <input type="text" 
         class="form-control"
         [(ngModel)]="searchTerm" 
         (input)="filterOptions()"
         (focus)="showDropdown = true"
         (blur)="hideDropdownWithDelay()"
         placeholder="Select an option">

  <ul *ngIf="showDropdown && filteredOptions.length > 0" 
      class="dropdown-menu show w-100" 
      style="position: absolute; top: 100%;">
    <li *ngFor="let option of filteredOptions" 
        class="dropdown-item"
        (click)="selectOption(option)">
      {{ option.name }}
    </li>
  </ul>

  <div *ngIf="selectedOption" class="mt-2">
    <strong>Selected:</strong> {{ selectedOption.name }} (ID: {{ selectedOption.id }})
  </div>
</div>
```

### **2. Update Component Logic (`autocomplete-select.component.ts`)**
```typescript
import { Component } from '@angular/core';

interface Option {
  id: number;
  name: string;
}

@Component({
  selector: 'app-autocomplete-select',
  templateUrl: './autocomplete-select.component.html',
  styleUrls: ['./autocomplete-select.component.css']
})
export class AutocompleteSelectComponent {
  searchTerm: string = '';
  showDropdown: boolean = false;
  selectedOption: Option | null = null;

  options: Option[] = [
    { id: 1, name: 'Apple' },
    { id: 2, name: 'Banana' },
    { id: 3, name: 'Cherry' },
    { id: 4, name: 'Date' },
    { id: 5, name: 'Grape' },
    { id: 6, name: 'Mango' },
    { id: 7, name: 'Orange' }
  ];

  filteredOptions: Option[] = [...this.options];

  filterOptions() {
    if (!this.searchTerm) {
      this.filteredOptions = [...this.options];
    } else {
      this.filteredOptions = this.options.filter(option =>
        option.name.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    }
    this.showDropdown = this.filteredOptions.length > 0;
  }

  selectOption(option: Option) {
    this.selectedOption = option;
    this.searchTerm = option.name;
    this.showDropdown = false;
  }

  hideDropdownWithDelay() {
    setTimeout(() => {
      this.showDropdown = false;
    }, 200);
  }
}
```

### **3. Updated Behavior**
- **Each option is an object** with `id` and `name`.
- **When a user selects an option, the entire object is stored** in `selectedOption`.
- **The dropdown displays names**, but the underlying value is an object.

---

### **Next Steps**
Do you need additional features like:
- Keyboard navigation?
- Async data fetching?
- Multi-select functionality?
