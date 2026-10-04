import os
import io
import pandas as pd
import matplotlib.pyplot as plt

cleaned_lines = []
with open('results/results.csv', 'r', encoding='utf-8') as f:
    header = f.readline()
    cleaned_lines.append(header)
    for line in f:
        parts = line.strip().split(',')
        if len(parts) == 9:
            workload, variant, structure, n, t1, t2, steps, moves, comp = parts
            time_ms = f"{t1}.{t2}"
            fixed_line = f"{workload},{variant},{structure},{n},{time_ms},{steps},{moves},{comp}\n"
            cleaned_lines.append(fixed_line)
        else:
            cleaned_lines.append(line)

df = pd.read_csv(io.StringIO(''.join(cleaned_lines)))
print(f"Successfully loaded rows: {len(df)}")

os.makedirs('results/plots', exist_ok=True)

plt.figure(figsize=(8, 5))
w1 = df[df['workload'] == 'W1']
for struct in w1['structure'].unique():
    subset = w1[w1['structure'] == struct]
    plt.plot(subset['n'], subset['time_ms'], marker='o', label=struct)
plt.title('W1: Random Access Performance')
plt.xlabel('n')
plt.ylabel('Time (ms)')
plt.xscale('log')
plt.yscale('log')
plt.legend()
plt.grid(True, which="both", ls="--")
plt.savefig('results/plots/w1_random_access.png')
plt.close()

plt.figure(figsize=(8, 5))
w2 = df[df['workload'] == 'W2']
for struct in w2['structure'].unique():
    subset = w2[w2['structure'] == struct]
    plt.plot(subset['n'], subset['time_ms'], marker='o', label=struct)
plt.title('W2: Search (Contains) Performance')
plt.xlabel('n')
plt.ylabel('Time (ms)')
plt.xscale('log')
plt.yscale('log')
plt.legend()
plt.grid(True, which="both", ls="--")
plt.savefig('results/plots/w2_search.png')
plt.close()

plt.figure(figsize=(8, 5))
w3 = df[df['workload'] == 'W3']
if 'variant' in w3.columns:
    for (struct, variant), group in w3.groupby(['structure', 'variant']):
        plt.plot(group['n'], group['time_ms'], marker='o', label=f'{struct} ({variant})')
else:
    for struct in w3['structure'].unique():
        subset = w3[w3['structure'] == struct]
        plt.plot(subset['n'], subset['time_ms'], marker='o', label=struct)
plt.title('W3: Insert & Remove Performance')
plt.xlabel('n')
plt.ylabel('Time (ms)')
plt.xscale('log')
plt.yscale('log')
plt.legend()
plt.grid(True, which="both", ls="--")
plt.savefig('results/plots/w3_insert_remove.png')
plt.close()

plt.figure(figsize=(8, 5))
w4 = df[df['workload'] == 'W4']
for struct in w4['structure'].unique():
    subset = w4[w4['structure'] == struct]
    plt.plot(subset['n'], subset['time_ms'], marker='o', label=struct)
plt.title('W4: Priority Processing Performance')
plt.xlabel('n')
plt.ylabel('Time (ms)')
plt.xscale('log')
plt.yscale('log')
plt.legend()
plt.grid(True, which="both", ls="--")
plt.savefig('results/plots/w4_priority_processing.png')
plt.close()

print("All plots generated successfully!")