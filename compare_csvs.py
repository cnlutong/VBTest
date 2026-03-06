import csv

def analyze_csv(file_path):
    print(f"--- Analyzing {file_path} ---")
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            lines = f.readlines()
            
        print(f"Total lines: {len(lines)}")
        
        # Check raw lines for column count (without proper CSV parsing)
        bad_lines = []
        for i, line in enumerate(lines):
            line = line.strip()
            if not line: continue
            # Count commas that are outside quotes (simple heuristic)
            in_quote = False
            commas = 0
            for char in line:
                if char == '\"':
                    in_quote = not in_quote
                elif char == ',' and not in_quote:
                    commas += 1
            if commas != 2:
                bad_lines.append((i+1, line, commas + 1))
        
        print(f"Lines with wrong column count (expected 3): {len(bad_lines)}")
        if bad_lines:
            print("Examples of bad lines:")
            for i in range(min(5, len(bad_lines))):
                print(f"  Line {bad_lines[i][0]}: cols={bad_lines[i][2]} | {bad_lines[i][1]}")
                
    except Exception as e:
        print(f"Error: {e}")
    print("\n")

for f in [
    r"z:\code\VBTest\Archived test vocabulary\test_v9.csv",
    r"z:\code\VBTest\Archived test vocabulary\test_v9.5.csv",
    r"z:\code\VBTest\test_v9.6.csv"
]:
    analyze_csv(f)
